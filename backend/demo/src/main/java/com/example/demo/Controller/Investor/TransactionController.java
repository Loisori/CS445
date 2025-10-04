package com.example.demo.Controller.Investor;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.TransactionDTO;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.Users;
import com.example.demo.Entity.pledge;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.TransactitonService;
import com.example.demo.Sercive.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Entity.pledge;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/Transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactitonService transactitonService;
    private final PledgeService pledgeService;
    private final UserService userService;
    private final ProjectsService projectsService;


    @PostMapping("/create")
    public ResponseEntity<Apireponsi<Transaction>> createTransaction(@RequestBody TransactionDTO transaction) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users users = userService.getUserByUsername(username);
        try {
            pledge p = pledgeService.GetOledge(transaction.getPledgeId());
            if (p == null) {
                Apireponsi<Transaction> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "pledge không tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Projects project = projectsService.GetidProject(p.getProject().getProjectId());
            if (users.getUserId() != p.getInvestor().getUserId()) {
                Apireponsi<Transaction> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "không có quyền tạo thanh toán cam kết này", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            if (project.getEndDate() != null && LocalDateTime.now().isAfter(project.getEndDate())) {
                Apireponsi<Transaction> erorr1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Thời gian dự án đã hết hạn", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr1);
            }
            Double Amount = transaction.getAmount();

            Transaction transaction1 = new Transaction().builder().
                    createdAt(LocalDateTime.now())
                    .amount(Amount)
                    .status(Transaction.TransactionStatus.PENDING).
                    capturedAt(null).
                    pledge(p).
                    build();
            transactitonService.SaveTransaction(transaction1);
            Apireponsi<Transaction> repon = new Apireponsi<>(HttpStatus.OK, "Tạo thành toán thành công", transaction1, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);


        } catch (Exception e) {
            Apireponsi<Transaction> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr2", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<Apireponsi<Transaction>> payTransaction(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userService.getUserByUsername(username);

        try {
            Transaction transaction = transactitonService.GetTransaction(id);
            if (transaction == null) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại thanh toán", null, null)
                );
            }

            if (transaction.getStatus() != Transaction.TransactionStatus.PENDING) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Transaction đã được thanh toán", null, null)
                );
            }

            pledge pledge = pledgeService.GetOledge(transaction.getPledge().getPledgeId());
            if (pledge == null) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại Cam kết", null, null)
                );
            }

            // Kiểm tra quyền của user
            if (!user.getUserId().equals(pledge.getInvestor().getUserId())) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không có quyền thanh toán cam kết này", null, null)
                );
            }
            Projects project = projectsService.GetidProject(pledge.getProject().getProjectId());
            if (project.getEndDate() != null && LocalDateTime.now().isAfter(project.getEndDate())) {
                Apireponsi<Transaction> erorr1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Thời gian dự án đã hết hạn", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr1);
            }

            // Kiểm tra số tiền thanh toán
            if (transaction.getAmount() > pledge.getRemaining()) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Số tiền thanh toán vượt quá số tiền còn lại", null, null)
                );
            }

            // Kiểm tra ví đủ tiền
            if (user.getWallet() < transaction.getAmount()) {
                return ResponseEntity.badRequest().body(
                        new Apireponsi<>(HttpStatus.BAD_REQUEST, "Số tiền trong ví không đủ", null, null)
                );
            }

            // Thanh toán
            user.setWallet(user.getWallet() - transaction.getAmount());
            pledge.setRemaining(pledge.getRemaining() - transaction.getAmount());

            // Nếu pledge hoàn tất
            if (pledge.getRemaining() <= 0) {
                pledge.setRemaining(0.0);
                pledge.setStatus(com.example.demo.Entity.pledge.PledgeStatus.PAID);

                // Cập nhật dự án

                if (project != null) {
                    project.setCollectedAmount(project.getCollectedAmount() + transaction.getAmount());

                    // Nếu đạt hoặc vượt goalAmount → SUCCESS
                    if (Math.abs(project.getCollectedAmount() - project.getGoalAmount()) < 0.01) {
                        project.setStatus(Projects.statusprojects.SUCCESS);
                    }

                    projectsService.SaveProject(project); // lưu sau cùng
                }
            }

            // Cập nhật transaction
            transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
            transaction.setCapturedAt(LocalDateTime.now());

            // Lưu dữ liệu
            userService.SaveUser(user);
            pledgeService.SaveOledge(pledge);
            transactitonService.SaveTransaction(transaction);

            return ResponseEntity.ok(
                    new Apireponsi<>(HttpStatus.OK, "Thanh toán thành công", transaction, null)
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new Apireponsi<>(HttpStatus.BAD_REQUEST, "Lỗi thanh toán: " + e.getMessage(), null, null)
            );
        }
    }

}

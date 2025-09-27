package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.NapTienDTO;
import com.example.demo.Entity.TransactionHistory;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.TransactionHistoryService;
import com.example.demo.Sercive.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TransactionHistoryService transactionHistoryService;

    @PutMapping("/NapTien")
    public ResponseEntity<Apireponsi<Users>> NapTien(@RequestBody NapTienDTO tien) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);
        try {
            Double vi = tien.getTien() + user.getWallet();
            user.setWallet(vi);
            userService.SaveUser(user);
            TransactionHistory transactionHistory = new TransactionHistory().builder()
                    .amount(tien.getTien())
                    .createdAt(LocalDateTime.now())
                    .status(TransactionHistory.TransactionStatus.SUCCESS)
                    .type(TransactionHistory.TransactionType.NAP_TIEN)
                    .user(user)
                    .build();
            transactionHistoryService.SaveTransactionHistory(transactionHistory);
            Apireponsi<Users> repon = new Apireponsi<>(HttpStatus.OK, "Nạp tiền thanành công", user, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Users> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }


    }

    @PutMapping("/RutTien")
    public ResponseEntity<Apireponsi<Users>> RutTien(@RequestBody NapTienDTO tien) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);
        try {
            Double vi = user.getWallet() - tien.getTien();

            if (vi >= 0) {
                user.setWallet(vi);
                userService.SaveUser(user);
                Apireponsi<Users> repon = new Apireponsi<>(HttpStatus.OK, "Nạp tiền thanành công", user, null);

                TransactionHistory transactionHistory1 = new TransactionHistory().builder()
                        .amount(tien.getTien())
                        .createdAt(LocalDateTime.now())
                        .status(TransactionHistory.TransactionStatus.SUCCESS)
                        .type(TransactionHistory.TransactionType.RUT_TIEN)
                        .user(user)
                        .build();
                transactionHistoryService.SaveTransactionHistory(transactionHistory1);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            } else {
                Apireponsi<Users> erorr = new Apireponsi<>(HttpStatus.OK, "Không đủ số dư", user, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);

            }
        } catch (Exception e) {
            Apireponsi<Users> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }


    }

}

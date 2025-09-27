package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.PayoutDTO;
import com.example.demo.Entity.Payout;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.PayoutService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/Payout")
@RequiredArgsConstructor
public class PayoutController {
    private final PayoutService payoutService;
    private final UserService userService;
    private final ProjectsService projectsService;

    @PostMapping("/Create")
    public ResponseEntity<Apireponsi<Payout>> CreatePayout(@RequestBody PayoutDTO payoutDTO) {
        try {
            Projects projects = projectsService.GetidProject(payoutDTO.getId_payout());
            if (projects == null) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không Có projects", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            Users creator = userService.getUserById(projects.getCreator().getUserId());
            if (creator == null) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không Có Cretor", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            if (!projects.getStatus().equals(Projects.statusprojects.SUCCESS)) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Dự án chưa gọi vốn thành công hoặc có thể bị fail", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            if(payoutService.CheckPayout(payoutDTO.getId_payout())){
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Projects này đã create Payout", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            Double gross_Amount = projects.getGoalAmount();
            Double platform_Fee = gross_Amount * payoutDTO.getPercent() / 100.0;
            Double net_Amount = gross_Amount - platform_Fee;
            Payout payout = new Payout().builder()
                    .grossAmount(gross_Amount)
                    .users(creator)
                    .project(projects)
                    .platformFee(platform_Fee)
                    .netAmount(net_Amount)
                    .payoutDate(null)
                    .status(Payout.statusPayout.PENDING)
                    .createdAt(LocalDateTime.now())
                    .build();
            payoutService.SavePayout(payout);
            Apireponsi<Payout> repon = new Apireponsi<>(HttpStatus.CREATED, "Payout created", payout, null);
            return ResponseEntity.status(HttpStatus.CREATED).body(repon);
        } catch (Exception e) {
            Apireponsi<Payout> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @PutMapping("/Pay/{id}")
    public ResponseEntity<Apireponsi<Payout>> pay(@PathVariable Long id) {
        try {
            Payout payout = payoutService.GetPayout(id);
            if (payout == null) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại Payout có id:" + id, null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            Users user = userService.getUserById(payout.getUsers().getUserId());
            if (user == null) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại Creator:", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            if(payout.getStatus().equals(Payout.statusPayout.SUCCESS)) {
                Apireponsi<Payout> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Đã Thanh Toán:", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            user.setWallet(user.getWallet() + payout.getNetAmount());
            userService.SaveUser(user);
            payout.setStatus(Payout.statusPayout.SUCCESS);
            payout.setPayoutDate(LocalDateTime.now());
            payoutService.SavePayout(payout);
            Apireponsi<Payout> repon = new Apireponsi<>(HttpStatus.OK, "Thanh Toán thành công Cho Creator", payout, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<Payout> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
}



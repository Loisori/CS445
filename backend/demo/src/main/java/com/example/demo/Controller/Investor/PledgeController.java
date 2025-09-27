package com.example.demo.Controller.Investor;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.pledgeDTO;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Reward;
import com.example.demo.Entity.Users;
import com.example.demo.Entity.pledge;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;
import com.example.demo.Sercive.UserService;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/pledge"})
@RequiredArgsConstructor
public class PledgeController {

    private final PledgeService pledgeService;

    private final ProjectsService projectsService;

    private final RewardService rewardService;

    private final UserService userService;


    @PostMapping("/Create")
    public ResponseEntity<Apireponsi<pledge>> Createpledge(@RequestBody pledgeDTO pledgeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.getUserByUsername(auth.getName());

        try {
            // Lấy reward và project
            Reward reward = rewardService.getRewardId(pledgeDTO.getReward_id());
            Projects project = projectsService.GetidProject(pledgeDTO.getProject_id());

            if (reward == null) {
                return badRequest("Không tồn tại reward");
            }
            if (project == null) {
                return badRequest("Không tồn tại project");
            }
            if (!project.getStatus().equals(Projects.statusprojects.OPEN)) {
                return badRequest("Dự án đã đầu tư hết");
            }
            // Kiểm tra thời gian dự án
            if (project.getEndDate() != null && LocalDateTime.now().isAfter(project.getEndDate())) {
                return badRequest("Dự án đã hết hạn");
            }

            double remainingToGoal = project.getGoalAmount() - project.getCollectedAmount();
            if (pledgeDTO.getAmount() > remainingToGoal) {
                return badRequest("Số tiền đầu tư vượt quá số tiền kêu gọi còn lại của dự án");
            }

            // Tạo pledge
            pledge newPledge = pledge.builder()
                    .amount(pledgeDTO.getAmount())
                    .remaining(pledgeDTO.getAmount())
                    .status(pledge.PledgeStatus.PENDING)
                    .investor(user)
                    .project(project)
                    .reward(reward)
                    .createdAt(LocalDateTime.now())
                    .build();

            // Cập nhật pledgedAmount và lưu
            project.setPledgedAmount(project.getPledgedAmount() + pledgeDTO.getAmount());
            projectsService.SaveProject(project);
            pledgeService.SaveOledge(newPledge);

            return ResponseEntity.ok(new Apireponsi<>(HttpStatus.OK, "Create thành công", newPledge, null));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new Apireponsi<>(HttpStatus.BAD_REQUEST, "Lỗi tạo pledge", null, e.getMessage()));
        }
    }

    // Helper method để gọn code
    private ResponseEntity<Apireponsi<pledge>> badRequest(String message) {
        return ResponseEntity.badRequest().body(new Apireponsi<>(HttpStatus.BAD_REQUEST, message, null, null));
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<Apireponsi<pledge>> getPledgeById(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);

        pledge p = pledgeService.GetOledge(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }


        if (user.getRole().getName().equals("INVESTOR")) {
            if (!p.getInvestor().getUserId().equals(user.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new Apireponsi<>(HttpStatus.FORBIDDEN, "Bạn không có quyền xem pledge này", null, null));
            }
        }


        if (user.getRole().getName().equals("CREATOR")) {
            if (!p.getProject().getCreator().getUserId().equals(user.getUserId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new Apireponsi<>(HttpStatus.FORBIDDEN, "Bạn không có quyền xem pledge này", null, null));
            }
        }


        return ResponseEntity.ok(new Apireponsi<>(HttpStatus.OK, "Lấy pledge thành công", p, null));
    }


    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Apireponsi<pledge>> DeletePledge(@PathVariable Long id) {
        try {
            pledge pledge1 = this.pledgeService.GetOledge(id);
            if (pledge1 == null) {
                Apireponsi<pledge> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Cam kết không tồn tại", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                this.pledgeService.DeleteOledge(id);
                Apireponsi<pledge> repon = new Apireponsi(HttpStatus.BAD_REQUEST, "Xoá Thành công cam kết :", pledge1, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception e) {
            Apireponsi<pledge> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("/showRemaining/{pledgeId}")
    public ResponseEntity<Apireponsi<Double>> ShowRemaining(@PathVariable("pledgeId") Long pledge_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userService.getUserByUsername(username);
        try {
            pledge pledge1 = this.pledgeService.GetOledge(pledge_id);
            if (pledge1 == null) {
                Apireponsi<Double> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại cam kết", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            if (!user.getUserId().equals(pledge1.getInvestor().getUserId())) {
                Apireponsi<Double> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không có quyền xem cam kết bày", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Double remaining = pledge1.getRemaining();
            Apireponsi<Double> repon = new Apireponsi<>(HttpStatus.OK, "Số tiền Còn lại cần thanh toán:" + remaining, remaining, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<Double> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }

    }

}

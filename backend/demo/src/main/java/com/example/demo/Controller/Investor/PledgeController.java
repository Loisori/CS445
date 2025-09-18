package com.example.demo.Controller.Investor;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;
import com.example.demo.Sercive.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Entity.pledge;
import com.example.demo.DTO.pledgeDTO;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/investor")
public class PledgeController {
    @Autowired
    private PledgeService pledgeService;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private UserService userService;


    @PostMapping("/CreatePledge")
    public ResponseEntity<Apireponsi<pledge>> Createpledge(@RequestBody pledgeDTO pledgeDTO) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Users user  = userService .getUserByUsername(username);

            pledge pledges = new pledge();
            pledges.setAmount(pledgeDTO.getAmount());
            pledges.setStatus(pledgeDTO.getStatus());
            pledges.setInvestor(user);
            pledges.setProject(projectsService.GetidProject(pledgeDTO.getProject_id()));
            pledges.setReward(rewardService.getRewardId(pledgeDTO.getReward_id()));
            pledges.setCreatedAt(LocalDateTime.now());
            pledgeService.SaveOledge(pledges);
            Apireponsi<pledge> repon = new Apireponsi<>(HttpStatus.OK, "Create Thành Công", pledges, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<pledge> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
@GetMapping("/GetPledge/{id}")
    public ResponseEntity<Apireponsi<pledge>> GetPledge(@PathVariable Long id) {
        try {
            pledge pledge1 = pledgeService.GetOledge(id);
            if(pledge1 == null) {
                Apireponsi<pledge> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Cam kết không tồn tại",null,null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Apireponsi<pledge> repon = new Apireponsi<>(HttpStatus.OK,"Cam kết Cần Lấy ", pledge1, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        }
        catch (Exception e) {
            Apireponsi<pledge> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }

}
@DeleteMapping("/DeletePledge/{id}")
    public ResponseEntity<Apireponsi<pledge>> DeletePledge(@PathVariable Long id) {
        try {
            pledge pledge1 = pledgeService.GetOledge(id);
            if(pledge1 == null) {
                Apireponsi<pledge> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Cam kết không tồn tại",null,null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            pledgeService.DeleteOledge(id);
            Apireponsi<pledge> repon = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Xoá Thành công cam kết :",pledge1,null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        }
        catch (Exception e) {
            Apireponsi<pledge> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
}

}

package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.RewardDTO;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Reward;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class RewardController {
    @Autowired
    private RewardService rewardService;
    @Autowired
    private ProjectsService projectsService;

    @PostMapping("/CreateReward")
    public ResponseEntity<Apireponsi<Reward>> CreateReward(@RequestBody RewardDTO rewardDTO) {
        try {
            if (rewardService.existReward(rewardDTO.getTitle())) {
                Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Phường thưởng đã tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Reward reward = new Reward();
            reward.setTitle(rewardDTO.getTitle());
            reward.setDescription(rewardDTO.getDescription());
            reward.setAmountRequired(rewardDTO.getAmountRequired());
            reward.setCreatedAt(LocalDateTime.now());
            reward.setUpdatedAt(LocalDateTime.now());
            reward.setQuantityLimit(rewardDTO.getQuantityLimit());
            Projects project = projectsService.GetidProject(rewardDTO.getProject_id());
            reward.setProject(project);
            rewardService.SaveReward(reward);
            Apireponsi<Reward> repon = new Apireponsi<>(HttpStatus.CREATED, "Create Reward Thành Công", reward, null);
            return ResponseEntity.status(HttpStatus.CREATED).body(repon);
        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @DeleteMapping("/DeleteReward/{id}")
    public ResponseEntity<Apireponsi<Reward>> DeleteReward(@PathVariable Long id) {
        try {
            Reward reward = rewardService.getRewardId(id);
            if (reward == null) {
                Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại phần thương", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            rewardService.DeleteReward(id);
            Apireponsi<Reward> repon = new Apireponsi<>(HttpStatus.OK, "Xoá Thành Công Phần thưởng:" + reward.getTitle(), null, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @PutMapping("/UpdateReward")
    public ResponseEntity<Apireponsi<Reward>> UpdateReward(@RequestBody RewardDTO rewardDTO) {
        try {
            Reward reward = rewardService.GetReward(rewardDTO.getTitle());
            if (!rewardService.existReward(rewardDTO.getTitle())) {
                Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại Phường thưởng ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            reward.setTitle(rewardDTO.getTitle());
            reward.setDescription(rewardDTO.getDescription());
            reward.setAmountRequired(rewardDTO.getAmountRequired());
            reward.setUpdatedAt(LocalDateTime.now());
            reward.setQuantityLimit(rewardDTO.getQuantityLimit());
            Projects project = projectsService.GetidProject(rewardDTO.getProject_id());
            reward.setProject(project);
            rewardService.SaveReward(reward);
            Apireponsi<Reward> repon = new Apireponsi<>(HttpStatus.OK, "Update Thành Công", reward, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("/GetAllReward")
    public ResponseEntity<Apireponsi<List<Reward>>> GetAllReward() {
        try {
            List<Reward> rewards = rewardService.getAllReward();
            Apireponsi<List<Reward>> repon = new Apireponsi<>(HttpStatus.OK, "Get All Reward", rewards, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Reward>> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("/GetReward/{id}")
    public ResponseEntity<Apireponsi<Reward>> GetReward(@PathVariable Long id) {
        try {
            Reward reward = rewardService.getRewardId(id);
            if (reward == null) {
                Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Apireponsi<Reward> repon = new Apireponsi<>(HttpStatus.OK, "Get Reward Thành Công", reward, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
}

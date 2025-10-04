package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.RewardDTO;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Reward;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/Reward"})
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    private final ProjectsService projectsService;

   private final PledgeService pledgeService;



    @PostMapping({"/Create"})
    public ResponseEntity<Apireponsi<Reward>> CreateReward(@RequestBody RewardDTO rewardDTO) {
        try {
            if (this.rewardService.existReward(rewardDTO.getTitle())) {
                Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Phường thưởng đã tồn tại", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                Reward reward = new Reward();
                reward.setTitle(rewardDTO.getTitle());
                reward.setDescription(rewardDTO.getDescription());
                reward.setAmountRequired(rewardDTO.getAmountRequired());
                reward.setCreatedAt(LocalDateTime.now());
                reward.setUpdatedAt(LocalDateTime.now());
                reward.setQuantityLimit(rewardDTO.getQuantityLimit());
                Projects project = this.projectsService.GetidProject(rewardDTO.getProject_id());
                if (project == null) {
                    Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "projects không tồn tại", (Object)null, (String)null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
                } else {
                    reward.setProject(project);
                    this.rewardService.SaveReward(reward);
                    Apireponsi<Reward> repon = new Apireponsi(HttpStatus.CREATED, "Create Reward Thành Công", reward, (String)null);
                    return ResponseEntity.status(HttpStatus.CREATED).body(repon);
                }
            }
        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object)null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @DeleteMapping({"/Delete/{id}"})
    public ResponseEntity<Apireponsi<Reward>> DeleteReward(@PathVariable Long id) {
        try {
            Reward reward = this.rewardService.getRewardId(id);
            if (reward == null) {
                Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại phần thương", (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                this.pledgeService.DeleteReward(id);
                this.rewardService.DeleteReward(id);
                Apireponsi<Reward> repon = new Apireponsi(HttpStatus.OK, "Xoá Thành Công Phần thưởng:" + reward.getTitle(), (Object)null, (String)null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception e) {
            Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object)null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }  @PutMapping("/Update/{id}")
    public ResponseEntity<Apireponsi<Reward>> UpdateReward(@PathVariable Long id,@RequestBody RewardDTO rewardDTO) {
        try {
            Reward reward = rewardService.getRewardId(id);
            if (reward == null) {
                Apireponsi<Reward> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại phần thương", (Object)null, (String)null);
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
    @GetMapping("/list/{id}")
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

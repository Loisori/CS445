//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Reward;
import com.example.demo.Repository.RewardRepository;
import com.example.demo.Sercive.impl.RewardServiceimpl;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardService implements RewardServiceimpl {

    private final RewardRepository rewardRepository;



    public void deleteProjects(Long id) {
        this.rewardRepository.deleteByProjectId(id);
    }

    public List<Reward> getAllReward() {
        return this.rewardRepository.findAll();
    }

    public Reward getRewardId(Long id) {
        return (Reward)this.rewardRepository.findById(id).orElse(null);
    }

    public Boolean existReward(String title) {
        return this.rewardRepository.existsBytitle(title);
    }

    public void SaveReward(Reward reward) {
        this.rewardRepository.save(reward);
    }

    public Reward GetReward(String title) {
        return this.rewardRepository.findBytitle(title);
    }

    public void DeleteReward(Long id) {
        this.rewardRepository.deleteById(id);
    }
}

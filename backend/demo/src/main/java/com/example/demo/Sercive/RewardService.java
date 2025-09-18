package com.example.demo.Sercive;

import com.example.demo.Entity.Reward;
import com.example.demo.Repository.RewardRepository;
import com.example.demo.Sercive.impl.RewardServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService implements RewardServiceimpl {
    @Override
    public List<Reward> getAllReward() {
        return  rewardRepository.findAll();
    }

    @Override
    public Reward getRewardId(Long id) {
        return rewardRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existReward(String title) {
        return rewardRepository.existsBytitle(title);
    }

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public void SaveReward(Reward reward) {
        rewardRepository.save(reward);
    }

    @Override
    public Reward GetReward(String title) {
        return rewardRepository.findBytitle(title);
    }

    @Override
    public void DeleteReward(Long id) {
        rewardRepository.deleteById(id);

    }
}

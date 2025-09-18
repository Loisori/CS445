package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Reward;

import java.util.List;

public interface RewardServiceimpl {
    public void SaveReward(Reward reward);

    public Reward GetReward(String title);

    public void DeleteReward(Long id);
    public Boolean existReward(String title);
    public Reward getRewardId(Long id);
public List<Reward> getAllReward();

}

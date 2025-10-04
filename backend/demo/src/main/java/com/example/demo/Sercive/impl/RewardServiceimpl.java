//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Reward;
import java.util.List;

public interface RewardServiceimpl {
    void SaveReward(Reward reward);

    Reward GetReward(String title);

    void DeleteReward(Long id);

    Boolean existReward(String title);

    Reward getRewardId(Long id);

    List<Reward> getAllReward();

    void deleteProjects(Long id);
}

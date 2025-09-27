//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Projects;
import com.example.demo.Entity.pledge;

import java.util.List;

public interface pledgeServiceimpl {
    void refundamout(Long projectId);

    void SaveOledge(pledge pledge1);

    pledge GetOledge(Long id);

    List<pledge> GetAllOledges();

    void deleteProjects(Long id);

    void DeleteOledge(Long id);

    void DeleteReward(Long id);
}

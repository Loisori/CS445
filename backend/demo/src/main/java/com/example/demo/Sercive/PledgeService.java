//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Entity.pledge;
import com.example.demo.Repository.PledgeRepository;
import com.example.demo.Sercive.impl.pledgeServiceimpl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PledgeService implements pledgeServiceimpl {
    @Override
    public void refundamout(Long projectId) {
        List<pledge> pledges1 = pledgeRepository.findByProjects_Id(projectId);
        for (pledge pledge : pledges1) {
            Users investor = pledge.getInvestor();
            Double refundamout = pledge.getAmount() - pledge.getRemaining();
            investor.setWallet(investor.getWallet() + refundamout);
            userService.SaveUser(investor);
            pledge.setStatus(com.example.demo.Entity.pledge.PledgeStatus.REFUND);
            SaveOledge(pledge);
        }
    }


    private final PledgeRepository pledgeRepository;
    private final UserService userService;


    public void DeleteReward(Long id) {
        this.pledgeRepository.deleteByRewardId(id);
    }

    public void DeleteOledge(Long id) {
        this.pledgeRepository.deleteById(id);
    }

    public void deleteProjects(Long id) {
        this.pledgeRepository.deleteByProjectId(id);
    }

    public void SaveOledge(pledge pledge1) {
        this.pledgeRepository.save(pledge1);
    }

    public pledge GetOledge(Long id) {
        return (pledge) this.pledgeRepository.findById(id).orElse(null);
    }

    public List<pledge> GetAllOledges() {
        return this.pledgeRepository.findAll();
    }
}

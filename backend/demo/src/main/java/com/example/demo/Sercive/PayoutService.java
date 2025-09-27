//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive;

import com.example.demo.Entity.Payout;
import com.example.demo.Repository.PayoutRepository;
import com.example.demo.Sercive.impl.PayoutServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutService implements PayoutServiceimpl {
    @Override
    public Boolean CheckPayout(Long id) {
        return payoutRepository.existsByProject_ProjectId(id);
    }

    @Override
    public Payout GetPayout(Long id) {
        return payoutRepository.findById(id).orElse(null);
    }

    @Override
    public void SavePayout(Payout payout) {
        payoutRepository.save(payout);
    }

    private final PayoutRepository payoutRepository;


    public void DeleteProjects(Long id) {
        this.payoutRepository.deleteByProjectId(id);
    }
}

package com.example.demo.Sercive;
import com.example.demo.Entity.pledge;
import com.example.demo.Repository.PledgeRepository;
import com.example.demo.Sercive.impl.pledgeServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PledgeService implements pledgeServiceimpl{
    @Autowired
    private PledgeRepository pledgeRepository;
    @Override
    public void DeleteOledge(Long id) {
pledgeRepository.deleteById(id);
    }

    @Override
    public void SaveOledge(pledge pledge1) {
        pledgeRepository.save(pledge1);


    }

    @Override
    public pledge GetOledge(Long id) {
        return pledgeRepository.findById(id).orElse(null);
    }

    @Override
    public List<pledge> GetAllOledges() {
        return pledgeRepository.findAll();
    }
}

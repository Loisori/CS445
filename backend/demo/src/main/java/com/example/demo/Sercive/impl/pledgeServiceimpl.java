package com.example.demo.Sercive.impl;
import com.example.demo.Entity.pledge;

import java.util.List;

public interface pledgeServiceimpl {
    public void SaveOledge(pledge pledge1);
    public pledge GetOledge(Long id);
    public List<pledge> GetAllOledges();
    public void DeleteOledge(Long id);


}

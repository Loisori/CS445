//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Sercive.impl;

import com.example.demo.Entity.Payout;

public interface PayoutServiceimpl {
    void DeleteProjects(Long id);

    void SavePayout(Payout payout);

    Payout GetPayout(Long id);

    Boolean CheckPayout(Long id);
}

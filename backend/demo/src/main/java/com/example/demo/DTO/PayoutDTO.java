//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.DTO;

import lombok.Builder;

import java.time.LocalDateTime;


public class PayoutDTO {
    private Long id_payout;
    private double percent;

    public Long getId_payout() {
        return id_payout;
    }

    public void setId_payout(Long id_payout) {
        this.id_payout = id_payout;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}

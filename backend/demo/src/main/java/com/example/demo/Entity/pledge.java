package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pledge")
public class pledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pledgeId;

    private Double amount;
    private String status;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "investor_id",nullable = false)
    private Users investor;
    @ManyToOne
    @JoinColumn(name = "Project_id",nullable = false)
    private Projects project;
    @ManyToOne
    @JoinColumn(name = "Reward_id",nullable = false)
    private Reward reward;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Users getInvestor() {
        return investor;
    }

    public void setInvestor(Users investor) {
        this.investor = investor;
    }

    public Long getPledgeId() {
        return pledgeId;
    }

    public void setPledgeId(Long pledgeId) {
        this.pledgeId = pledgeId;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


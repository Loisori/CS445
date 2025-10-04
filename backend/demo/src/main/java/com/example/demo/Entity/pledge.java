package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pledge")
@Builder
public class pledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pledgeId;

    public enum PledgeStatus {
        PENDING,   // vừa tạo, chờ thanh toán
        PAID,      // đã thanh toán thành công
        CANCELLED,  // user hủy pledge
        REFUND
    }

    private Double amount;
    @Enumerated(EnumType.STRING)
    private PledgeStatus status;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Users investor;
    @ManyToOne
    @JoinColumn(name = "Project_id", nullable = false)
    private Projects project;
    @ManyToOne
    @JoinColumn(name = "Reward_id", nullable = false)
    private Reward reward;
    // số tiền còn lại

    private Double remaining;

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

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

    public PledgeStatus getStatus() {
        return status;
    }

    public void setStatus(PledgeStatus status) {
        this.status = status;
    }
}


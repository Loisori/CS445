package com.example.demo.Entity;

import com.example.demo.DTO.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payout")
@Builder
public class Payout {
public enum  statusPayout{
    PENDING,
    SUCCESS
}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payoutId;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "creator_id",nullable = false)
    private Users users;

    private Double grossAmount;
    private Double platformFee;
    private Double netAmount;

    @Column(name = "payout_date")
    private LocalDateTime payoutDate;
    @Enumerated(EnumType.STRING)
    private statusPayout status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public LocalDateTime getPayoutDate() {
        return payoutDate;
    }

    public void setPayoutDate(LocalDateTime payoutDate) {
        this.payoutDate = payoutDate;
    }

    public Long getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(Long payoutId) {
        this.payoutId = payoutId;
    }

    public Double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Double platformFee) {
        this.platformFee = platformFee;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public statusPayout getStatus() {
        return status;
    }

    public void setStatus(statusPayout status) {
        this.status = status;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}

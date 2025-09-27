package com.example.demo.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Projects")
@Builder
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String title;

    private String description;
    private Double goalAmount;
    private Double pledgedAmount=0.0;
    private Double collectedAmount=0.0;
    @Enumerated(EnumType.STRING) // Hibernate sẽ lưu PENDING, OPEN... dạng chuỗi
    @Column(name = "status")
    private statusprojects status;

    private LocalDateTime createdAt;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Users getCreator() {
        return creator;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }
public  enum  statusprojects{
    PENDING,        // Đang chờ admin duyệt
    OPEN,           // Đang gây quỹ (đã duyệt, trong thời gian gọi vốn)
    SUCCESS,        // Gây quỹ thành công (đạt goalAmount trước endDate)
    REJECTED,  // Bị từ chối
    COMPLETED ,
    FAILED// Hoàn thành
}
    private String image;
    @ManyToOne
    @JoinColumn(name="Category_id",nullable = false)
    private Categories categories;
    @ManyToOne
    @JoinColumn(name="creator_id",nullable = false)
    private Users creator;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPledgedAmount() {
        return pledgedAmount;
    }

    public void setPledgedAmount(Double pledgedAmount) {
        this.pledgedAmount = pledgedAmount;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public statusprojects getStatus() {
        return status;
    }

    public void setStatus(statusprojects status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

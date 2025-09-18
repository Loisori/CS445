package com.example.demo.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Project {
    private String title;
    private String description;
    private Double goalAmount;
    private Double pledgedAmount;    // cho phép nhập
    private Double collectedAmount;  // cho phép nhập
    private Long category_id;
    private String Status;



    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }


    private LocalDateTime startDate;
    private LocalDateTime endDate;





    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
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

    public Double getPledgedAmount() {
        return pledgedAmount;
    }

    public void setPledgedAmount(Double pledgedAmount) {
        this.pledgedAmount = pledgedAmount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

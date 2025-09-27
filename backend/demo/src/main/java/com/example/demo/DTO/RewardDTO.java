//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.DTO;

public class RewardDTO {
    private String description;
    private Double amountRequired;
    private Integer quantityLimit;
    private String title;
    private Long project_id;

    public RewardDTO() {
    }

    public Long getProject_id() {
        return this.project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Double getAmountRequired() {
        return this.amountRequired;
    }

    public void setAmountRequired(Double amountRequired) {
        this.amountRequired = amountRequired;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityLimit() {
        return this.quantityLimit;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

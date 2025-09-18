package com.example.demo.DTO;


import jakarta.validation.constraints.NotEmpty;

public class Email {
    @jakarta.validation.constraints.Email(message = "email phải đúng định dạng")
    @NotEmpty(message = "email không được để trống")

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
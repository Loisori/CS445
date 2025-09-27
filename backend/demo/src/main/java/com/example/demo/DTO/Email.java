//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.DTO;

import jakarta.validation.constraints.NotEmpty;

public class Email {
    private @jakarta.validation.constraints.Email(
            message = "email phải đúng định dạng"
    ) @NotEmpty(
            message = "email không được để trống"
    ) String email;

    public Email() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

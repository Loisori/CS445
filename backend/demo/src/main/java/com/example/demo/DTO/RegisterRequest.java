//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    private @NotBlank(
            message = "Tên không được để trống"
    ) String name;
    private @NotBlank(
            message = "Mật khẩu không được để trống"
    )
    @Size(
            min = 6,
            message = "Mật khẩu phải ít nhất 6 ký tự"
    ) String password;
    private @NotBlank(
            message = "Email không được để trống"
    )
    @Email(
            message = "Email không hợp lệ"
    ) String email;
    private @NotBlank(
            message = "Số điện thoại không được để trống"
    ) String phone;

    public RegisterRequest(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public RegisterRequest() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

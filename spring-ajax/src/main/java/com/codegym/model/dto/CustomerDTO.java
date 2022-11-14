package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    @Pattern(regexp = "^\\d+$", message = "ID không hợp lệ")
    private String id;
    @NotEmpty(message = "Tên không được trống")
    @Column(name = "full_name", nullable = false)
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5-100 ký tự")
    private String fullName;

    @Email(message = "Địa chỉ email không đúng định dạng (vd: haibinh@gmail.com)!!!.")
    @Size(min = 5, max = 50, message = "Email có 5 đến 50 ký tự")
    @NotEmpty(message = "Email không được trống")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Phone không được trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Vui lòng nhập đúng định dạng số điện thoại (vd:0969455422)")
    private String phone;

    @NotEmpty(message = "Địa chỉ không được để trống!!")
    private String address;

    private String balance;
    private boolean deleted;
}

package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "full_name", nullable = false)
    @NotEmpty(message = "Họ tên không được để trống")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5-100 ký tự")
    private String fullName;

    @Email(message = "Địa chỉ email không đúng định dạng !!!.")
    @Size(min = 5, max = 50, message = "Email có 5 đến 50 ký tự (vd: haibinh@gmail.com)")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^0[0-9]{9}$", message = "Vui lòng nhập đúng định dạng số điện thoại (vd:0969455422)")
    private String phone;

    @NotEmpty(message = "Địa chỉ không được để trống!!")
    private String address;

    @Column(precision = 12, scale = 0, nullable = false, updatable = false)
    private BigDecimal balance;


    @OneToMany
    private List<Deposit> deposits;
    @OneToMany
    private List<Withdraw> withdraws;

    public Customer() {
    }

    public Customer(Long id, @NotEmpty String fullName,
                    @NotEmpty  @Email String email,
                    @NotEmpty String phone,
                    @NotEmpty String address,
                    BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }


}
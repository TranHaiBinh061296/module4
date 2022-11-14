package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {

    private Long id;

    @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số")
    @DecimalMin(value = "20000.0",message = "Số tiền tối thiểu là 20.000 VNĐ")
    @DecimalMax(value = "500000000", message = "Số tiền tối đa là 500.000.000 VNĐ")
    private String transactionAmount;

    @Pattern(regexp = "^\\d+$", message = "ID khách hàng không hợp lệ")
    private String customerId;
}
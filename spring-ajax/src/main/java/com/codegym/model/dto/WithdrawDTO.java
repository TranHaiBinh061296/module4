package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WithdrawDTO {

    private long id;
    @Pattern(regexp = "^\\d+$", message = "ID khách hàng không hợp lệ")
    @NotEmpty(message = "Vui lòng nhập ID khách hàng")
    private String customerId;
    @Pattern(regexp = "^\\d+$", message = "Số tiền không hợp lệ")
    @NotEmpty(message = "Vui lòng nhập số tiền")
    @DecimalMin(value = "20000.0",message = "Số tiền tối thiểu là 20000 VNĐ")
    @DecimalMax(value = "500000000", message = "Số tiền tối đa là 500.000.000 VNĐ")
    private String transactionAmount;
}

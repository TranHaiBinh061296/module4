package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO implements Validator {
    private long id;


    @Pattern(regexp = "^\\d+$", message = "ID người gửi không hợp lệ")
    @NotEmpty(message = "Vui lòng nhập ID người gửi")
    private String senderId;

    @NotEmpty(message = "ID người nhận không được để trống")
    @Pattern(regexp = "^\\d+$", message = "ID người nhận phải là số")
    private String recipientId;

    @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số")
    @DecimalMin(value = "20000.0",message = "Số tiền tối thiểu là 20000 VNĐ")
    @DecimalMax(value = "500000000", message = "Số tiền tối đa là 500.000.000 VNĐ")
    private String transferAmount;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}


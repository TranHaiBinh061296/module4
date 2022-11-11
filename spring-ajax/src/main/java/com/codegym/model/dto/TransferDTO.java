package com.codegym.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO implements Validator {

    @NotEmpty(message = "ID người nhận không được để trống")
//    @Size(min = 1, message = "ID người nhận không được để trống")
    @Pattern(regexp = "^\\d+$", message = "ID người nhận phải là số")
    private String recipientId;

    @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số")
    private String transferAmount;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}


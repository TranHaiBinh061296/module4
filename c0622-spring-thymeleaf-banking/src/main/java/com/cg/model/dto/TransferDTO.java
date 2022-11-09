package com.cg.model.dto;


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

//    public TransferDTO() {
//    }
//
//    public TransferDTO(@NotEmpty(message = "ID người nhận không được để trống") @Pattern(regexp = "^\\d+$", message = "ID người nhận phải là số") String recipientId, @Pattern(regexp = "^\\d+$", message = "Sô tiền gửi phải là số") String transferAmount) {
//        this.recipientId = recipientId;
//        this.transferAmount = transferAmount;
//    }
//
//    public String getRecipientId() {
//        return recipientId;
//    }

//    public void setRecipientId(String recipientId) {
//        this.recipientId = recipientId;
//    }
//
//    public String getTransferAmount() {
//        return transferAmount;
//    }
//
//    public void setTransferAmount(String transferAmount) {
//        this.transferAmount = transferAmount;
//    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}

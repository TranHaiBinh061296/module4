package com.cg.controller;


import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDepositService depositService;


    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        List<Customer> customers = customerService.findAllByDeletedIsFalse();

        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showSearchPage(@RequestParam String keySearch) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        keySearch = "%" + keySearch + "%";

        List<Customer> customers = customerService.findAllByFullNameLikeOrEmailLike(keySearch, keySearch);

        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @GetMapping("/update/{customerId}")
    public ModelAndView showCreatePage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());

        return modelAndView;
    }

    @GetMapping("/deposit/{cid}")
    public ModelAndView showDepositPage(@PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        modelAndView.addObject("deposit", new Deposit());

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());

        return modelAndView;
    }


    @GetMapping("/transfer/{cid}")
    public ModelAndView showTransferPage(@PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        modelAndView.addObject("transfer", new Transfer());

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("sender", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        List<Customer> recipients = customerService.findAllByDeletedIsFalseAndIdNot(cid);

        modelAndView.addObject("recipients", recipients);

        modelAndView.addObject("sender", customerOptional.get());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute Customer customer,  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        new Customer().validate(customer, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("errors", true);
            return modelAndView;
        }

        try {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);

            modelAndView.addObject("customer", new Customer());
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @PutMapping("/update/{cid}")
    public ModelAndView update(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("errors", true);
            return modelAndView;
        }

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        try {
            customer.setId(cid);
            customerService.save(customer);

            modelAndView.addObject("customer", customer);
        } catch (Exception e) {
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }

    @PostMapping("/deposit/{cid}")
    public ModelAndView deposit(@PathVariable Long cid, @Validated @ModelAttribute Deposit deposit, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");



        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
//            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        Customer customer = customerOptional.get();

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("errors", true);
            return modelAndView;
        }

        try {
            customerService.deposit(deposit, customer);

            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("success", "Gửi tiền thành công");
        } catch (Exception e) {
            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Thao tác không thành công, vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }


    @GetMapping("/delete/{customerId}")
    public ModelAndView showDeletepage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/delete");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());


        return modelAndView;
    }

    @PostMapping("/transfer/{senderId}")
    public ModelAndView transfer(@PathVariable Long senderId, @Validated @ModelAttribute TransferDTO transferDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        if (bindingResult.hasFieldErrors()) {
            Optional<Customer> senderOptional = customerService.findById(senderId);
            modelAndView.addObject("sender", senderOptional.get());

            modelAndView.addObject("transfer", new Transfer());

            List<Customer> recipients = customerService.findAllByDeletedIsFalseAndIdNot(senderId);
            modelAndView.addObject("recipients", recipients);

            modelAndView.addObject("errors", true);
            return modelAndView;
        }

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", new Customer());
            modelAndView.addObject("error", "ID người gửi không hợp lệ");

            return modelAndView;
        }

        List<Customer> recipients = customerService.findAllByDeletedIsFalseAndIdNot(senderId);

        Optional<Customer> recipient = customerService.findById(Long.parseLong(transferDTO.getRecipientId()));

//        Customer recipient = transfer.getRecipient();

        if (!recipient.isPresent()) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", "ID người nhận không hợp lệ");

            return modelAndView;
        }

        BigDecimal currentSenderBalance = senderOptional.get().getBalance();
//        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal transferAmount = BigDecimal.valueOf(Long.parseLong(transferDTO.getTransferAmount()));
        long fees = 10;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (currentSenderBalance.compareTo(transactionAmount) < 0) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", "Số dư tài khoản không đủ để thực hiện giao dịch");
            return modelAndView;
        }

        Transfer transfer = new Transfer();

        transfer.setId(0L);
        transfer.setSender(senderOptional.get());
        transfer.setRecipient(recipient.get());
        transfer.setTransferAmount(transferAmount);
        transfer.setFees(fees);
        transfer.setFeesAmount(feesAmount);
        transfer.setTransactionAmount(transactionAmount);
        customerService.transfer(transfer);

        Optional<Customer> newSenderOptional = customerService.findById(senderId);

        try {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", newSenderOptional.get());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("success", "Chuyển khoản thành công");
        } catch (Exception e) {
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", newSenderOptional.get());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", "Vui lòng liên hệ Administrator");
        }

        return modelAndView;
    }


    @DeleteMapping("/delete/{customerId}")
    public ModelAndView delete(@PathVariable Long customerId ,RedirectAttributesModelMap redirectAttributesModelMap ) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            modelAndView.setViewName("customer/delete");
            return modelAndView;
        }

        Customer customer = customerOptional.get();
        customer.setDeleted(true);
        customerService.save(customer);

        redirectAttributesModelMap.addFlashAttribute("success", "Xóa thành công");
        modelAndView.setViewName("redirect:" + "/customers");

        return modelAndView;
    }

}

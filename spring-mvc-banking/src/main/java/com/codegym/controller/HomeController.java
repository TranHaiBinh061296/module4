package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.Withdraw;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.transfer.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/customers", ""})
public class HomeController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransferService transferService;
    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/home");
        List<Customer> customers = customerService.findAllByDeletedIsFalse();
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
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("error","ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }
    }
    @GetMapping("/deposit/{cid}")
    public ModelAndView showDepositPage(@PathVariable Long cid,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");
        modelAndView.addObject("deposit", new Deposit());

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("error","ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());

        return modelAndView;
    }
    @GetMapping("/withdraw/{cid}")
    public ModelAndView showWithdrawPage(@PathVariable Long cid,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");
        modelAndView.addObject("withdraw", new Withdraw());

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("customer", new Customer());
            redirectAttributes.addFlashAttribute("error", "ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());

        return modelAndView;
    }
    @GetMapping("/transfer/{senderId}")
    public ModelAndView showTransferPage(@PathVariable long senderId,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();


        Optional<Customer> customerOptional = customerService.findById(senderId);

        if (!customerOptional.isPresent()) {
            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("error", "ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }
        else {
            modelAndView.setViewName("customer/transfer");
            List<Customer> recipients = customerService.findAllByIdNot(senderId);
            Transfer transfer = new Transfer();
            modelAndView.addObject("transfer", transfer);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("sender", customerOptional.get());
        }
        return modelAndView;
    }
    @GetMapping("/information")
    public ModelAndView showTransferInformation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transferInformation");
        List<Transfer> transfers = transferService.findAll();
        modelAndView.addObject("transfers", transfers);
        return modelAndView;
    }
    @GetMapping("/suspend/{id}")
    public ModelAndView showSuspendForm(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("customer/suspend");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("error", "ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }
    }
    @PostMapping("/suspend/{id}")
    public ModelAndView suspend(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("customer/suspend");
        }else {

            customer.setDeleted(true);
            customerService.save(customer);
            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("message","X??a th??ng tin th??nh c??ng!!!");

        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView save(@Validated @ModelAttribute Customer customer, BindingResult bindingResult, @PathVariable Long id,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("customer/edit");
        }else {
            customerService.save(customer);
            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("message","Ch???nh s???a th??nh c??ng!!!");
        }
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute Customer customer, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("customer/create");
        }else {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);

            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("success",true);
        }

        return modelAndView;
    }
    @PostMapping("/deposit/{cid}")
    public ModelAndView deposit(@Validated @ModelAttribute Deposit deposit, BindingResult bindingResult, @PathVariable Long cid,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customerOptional = customerService.findById(cid);

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("customer",customerOptional.get());
            modelAndView.setViewName("customer/deposit");
            return modelAndView;
        }

        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("customer/deposit");
            modelAndView.addObject("error", "ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }

        Customer customer = customerOptional.get();

        try {
            customerService.deposit(deposit, customer);
        } catch (Exception e) {
            modelAndView.setViewName("customer/deposit");
            modelAndView.addObject("deposit", new Deposit());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Thao t??c kh??ng th??nh c??ng, vui l??ng li??n h??? Administrator");
            return modelAndView;
        }
        modelAndView =  new ModelAndView("redirect:/customers");
        redirectAttributes.addFlashAttribute("message","N???p ti???n th??nh c??ng!!!");
        return modelAndView;
    }

    @PostMapping("/withdraw/{cid}")
    public ModelAndView withdraw(@Validated @ModelAttribute Withdraw withdraw, BindingResult bindingResult, @PathVariable Long cid,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("customer/withdraw");
            modelAndView.addObject("error", "ID kh??ch h??ng kh??ng h???p l???");
            return modelAndView;
        }
        Customer customer = customerOptional.get();
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("customer",customer);
            modelAndView.setViewName("customer/withdraw");
            return modelAndView;
        }
        if (customer.getBalance().compareTo(withdraw.getTransactionAmount()) < 0) {
            modelAndView.setViewName("customer/withdraw");
            modelAndView.addObject("withdraw", withdraw);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "S??? d?? t??i kho???n kh??ng ?????");
            return modelAndView;
        }

        try {
            customerService.withdraw(withdraw, customer);
        } catch (Exception e) {
            modelAndView.setViewName("customer/withdraw");
            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Thao t??c kh??ng th??nh c??ng, vui l??ng li??n h??? Administrator");
            return modelAndView;
        }
        modelAndView =  new ModelAndView("redirect:/customers");
        redirectAttributes.addFlashAttribute("message","R??t ti???n th??nh c??ng!!!");
        return modelAndView;
    }
    @PostMapping("/transfer/{senderId}")
    public ModelAndView doTransfer(@Validated @ModelAttribute Transfer transfer, BindingResult bindingResult, @PathVariable long senderId,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        List<Customer> recipients = customerService.findAllByIdNot(senderId);
        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView =  new ModelAndView("redirect:/customers");
            redirectAttributes.addFlashAttribute("error","ID ng?????i g???i kh??ng t???n t???i");
            return modelAndView;
        }

        Optional<Customer> recipientOptional = customerService.findById(transfer.getRecipient().getId());

        if (!recipientOptional.isPresent()) {
            modelAndView.setViewName("customer/transfer");

            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject("error", "ID ng?????i g???i kh??ng t???n t???i");
            return modelAndView;
        }

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.setViewName("customer/transfer");
            return modelAndView;
        }
        BigDecimal transferAmount;
        BigDecimal currentBalanceSender;
        BigDecimal transactionAmount;
        Customer sender;
        BigDecimal feesAmount;
        long fees = 0;
        try {
            sender = senderOptional.get();
            currentBalanceSender = sender.getBalance();
            Customer recipient = recipientOptional.get();

            transferAmount = transfer.getTransferAmount();

            if (transferAmount.compareTo(new BigDecimal(100000l)) < 0) {
                fees = 5;
            } else if (transferAmount.compareTo(new BigDecimal(500000l)) < 0) {
                fees = 8;
            } else {
                fees = 10;
            }
            feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100L));
            transactionAmount = transferAmount.add(feesAmount);
        } catch (Exception e) {
            modelAndView.setViewName("customer/transfer");
            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("sender", senderOptional.get());
            modelAndView.addObject("error", "Vui l??ng nh???p s??? ti???n chuy???n");
            return modelAndView;
        }



        if (currentBalanceSender.compareTo(transactionAmount) < 0) {
            modelAndView.setViewName("customer/transfer");

            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", "S??? d?? ng?????i g???i kh??ng ????? th???c hi???n giao d???ch");
            return modelAndView;
        }

        try {
            transfer.setId(0L);
            transfer.setSender(sender);
            transfer.setFees(fees);
            transfer.setFeesAmount(feesAmount);
            transfer.setTransactionAmount(transactionAmount);

            customerService.transfer(transfer);
        } catch (Exception e) {
            modelAndView.setViewName("customer/transfer");

            modelAndView.addObject("transfer", new Transfer());
            modelAndView.addObject("sender", sender);
            modelAndView.addObject("recipients", recipients);
            modelAndView.addObject("error", "Thao t??c kh??ng th??nh c??ng, vui l??ng li??n h??? Administrator");
            return modelAndView;
        }

        modelAndView =  new ModelAndView("redirect:/customers");
        redirectAttributes.addFlashAttribute("message","Chuy???n ti???n th??nh c??ng!!!");
        return modelAndView;
    }

}
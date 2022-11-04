package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Deposit;
import com.codegym.model.Withdraw;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.deposit.IDepositService;
import com.codegym.service.withdraw.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;



    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        List<Customer> customers = customerService.findAll();

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

    @PostMapping("/create")
    public ModelAndView create(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            return modelAndView;
        }
        try {
            customer.setId(0L);
            customer.setBalance(new BigDecimal(0L));
            customerService.save(customer);
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("error", true);
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/customer/edit");
            modelAndView.addObject("customer", customer.get());

            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/edit/{cid}")
    public ModelAndView updateCustomer(@PathVariable long cid, @Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");

        Optional<Customer> customerOptional = customerService.findById(cid);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", true);
        }
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            return modelAndView;
        }
        customer.setId(cid);
        Customer newCustomer = customerService.save(customer);
        modelAndView.addObject("success", true);
        modelAndView.addObject("customer", newCustomer);
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

    @PostMapping("/deposit/{cid}")
    public ModelAndView deposit(@ModelAttribute Deposit deposit, @PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        Optional<Customer> customerOptional = customerService.findById(cid);

        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }

        Customer customer = customerOptional.get();

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

    @GetMapping("/withdraw/{cid}")
    public ModelAndView showWithdrawPage(@PathVariable Long cid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/customer/withdraw");
        modelAndView.addObject("withdraw", new Withdraw());
        Optional<Customer> customerOptional = customerService.findById(cid);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }
        modelAndView.addObject("customer", customerOptional.get());
        return modelAndView;
    }

    @PostMapping("/withdraw/{cid}")
    public ModelAndView withdraw(@PathVariable("cid") Long cid, @ModelAttribute Withdraw withdraw) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");
        Optional<Customer> customerOptional = customerService.findById(cid);
        if (!customerOptional.isPresent()) {
            modelAndView.addObject("error", "ID khách hàng không hợp lệ");
            return modelAndView;
        }
        Customer customer = customerOptional.get();
        if (customerService.withdraw(withdraw, customer)) {
            try {

//           customerService.withdraw(withdraw, customer);
                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("success", "Rút tiền thành công");
            } catch (Exception e) {
                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("customer", customer);
                modelAndView.addObject("error", "Số tiền trong tài khoản không đủ !!!");
            }
        } else {
            modelAndView.addObject("withdraw", new Withdraw());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Số tiền trong tài khoản không đủ !!!");
        }

        return modelAndView;
    }

    @GetMapping("/suspension/{id}")
    public ModelAndView showSuspensionCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/suspension");

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            modelAndView.addObject("message", "No");
            return modelAndView;
        }
    }

    @PostMapping("/suspended/{id}")
    public ModelAndView suspendedCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/suspension");

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
//            customer.get().setDeleted(true);
            customerService.save(customer.get());
            modelAndView.addObject("customer", customerService.findById(id).get());
            modelAndView.addObject("success", true);
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }


}
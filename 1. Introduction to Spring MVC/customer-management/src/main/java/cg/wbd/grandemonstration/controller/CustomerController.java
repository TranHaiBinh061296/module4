package cg.wbd.grandemonstration.controller;


import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import cg.wbd.grandemonstration.service.CustomerServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService = CustomerServiceFactory.getInstance();

//    @GetMapping("/customers")
//    public String showList(HttpServletRequest request) {
//        List<Customer> customers = customerService.findAll();
//        request.setAttribute("customers", customers);
//        return "customers/list.jsp";
//    }
@GetMapping("/customers")
public ModelAndView showList() {
    ModelAndView modelAndView = new ModelAndView("customers/list.jsp");
    List<Customer> customers = customerService.findAll();
    modelAndView.addObject("customers", customers);
    return modelAndView;
}
}
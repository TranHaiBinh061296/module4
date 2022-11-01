package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductCPController {
    @GetMapping
    public ModelAndView showProductList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/product/list");
        return  modelAndView;
    }
    @GetMapping("/test")
    public ModelAndView showProductTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/product/test");
        return  modelAndView;
    }
    @GetMapping("/edit")
    public ModelAndView showProductEdit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/product/edit");
        return  modelAndView;
    }
}

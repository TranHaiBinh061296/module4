package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static jdk.nashorn.internal.objects.Global.Infinity;

@Controller
public class CalculatorController {
    @RequestMapping("/")
    public String exchange() {
        return "index";
    }

    @RequestMapping("/calculator")
    public String load(@RequestParam("first") Double first,
                       @RequestParam("second") Double second,
                       @RequestParam("operation") String operation,
                       Model model) {
        Double temp = Double.valueOf(0);
        String result = null;

        switch (operation) {
            case "+":
                temp = first + second;
                break;
            case "-":
                temp = first - second;
                break;
            case "*":
                temp = first * second;
                break;
            case "/":
                temp = first / second;
                break;
        }
        if (temp.equals(Infinity)) {
            result = "Can't divice 0";
        }
        if (result == null) {
            result = String.valueOf(temp);
        }
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("result", result);
        return "index";
    }

}

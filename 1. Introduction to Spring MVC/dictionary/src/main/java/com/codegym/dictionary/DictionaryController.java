package com.codegym.dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    @GetMapping("/dictionary")
    public String exchange() {
        return "index";
    }

    @PostMapping("/index")
    public String conver(@RequestParam("english") String english, Model model) {
        Map characters = new HashMap<>();
        characters.put("hello", "xin chào");
        characters.put("book", "Quyển sách");
        characters.put("pen", "Bút");
        characters.put("hate", "Ghét");
        String translate;
        if (characters.containsKey(english)) {
            translate = (String) characters.get(english);
        } else {
            translate = "Hiện chưa tìm thấy";
        }
        model.addAttribute("english",english);
        model.addAttribute("translate", translate);
        return "index";
    }
}

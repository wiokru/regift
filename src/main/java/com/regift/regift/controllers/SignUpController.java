package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SignUpController {

    @RequestMapping("/signup")
    public ModelAndView signup(Model model) {
        model.addAttribute("tempUser", new User());
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public void signUp(@ModelAttribute("tempUser") User user){
        System.out.println(user.getEmail());
    }
}
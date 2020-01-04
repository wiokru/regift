package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;

@RestController
public class SignUpController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/signup")
    public ModelAndView signup(Model model) {
        model.addAttribute("tempUser", new User());
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public void signUp(@ModelAttribute("name") String name){
        System.out.println(name);
    }
}
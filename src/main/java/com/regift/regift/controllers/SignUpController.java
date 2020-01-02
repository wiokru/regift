package com.regift.regift.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SignUpController {

    @RequestMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }
}
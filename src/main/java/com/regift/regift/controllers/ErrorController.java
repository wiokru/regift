package com.regift.regift.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorController {
    @GetMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("error");
    }
}

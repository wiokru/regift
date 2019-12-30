package com.regift.regift.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AboutRegiftController {

    @RequestMapping("/about")
    public ModelAndView aboutRegift(){
        return new ModelAndView("about");
    }
}
package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @PostMapping("/")
    public ModelAndView login(@ModelAttribute("email") String email,
                              @ModelAttribute("password") String password) {
        List<User> result;
        result = userRepository.findByEmail(email);
        if (!result.isEmpty()) {
            if (result.get(0).getPassword() == password) {
                System.out.println("XXXXXXXXXXXXXXXXXXX LOGIN SUCCESS");
            } else {
                //PASSWORD INCORRECT
                System.out.printf("YYYYYYYYYYYYYYYYYYYY wrong pass");
            }
        } else {
            System.out.println("ZZZZZZZZZZZZZZZZZZZZ user dont exist - plz sign up");
        }
        return new ModelAndView("redirect:/");
    }
}
package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @PostMapping("/")
    public ModelAndView login(Model model,
                      @ModelAttribute("email") String email,
                      @ModelAttribute("password") String password) {

            List<User> result;
            result = userRepository.findByEmail(email);
            if (!result.isEmpty()) {
                if (result.get(0).getPassword().equals(password)) {
                    System.out.println("XXXXXXXXXXXXXXXXXXX LOGIN SUCCESS");
                    return new ModelAndView("redirect:/user/" + result.get(0).getId().toString() + "/home");
                } else {
                    //PASSWORD INCORRECT
                    System.out.printf("YYYYYYYYYYYYYYYYYYYY wrong pass");
                    ModelAndView modelAndView = new ModelAndView("home");
                    modelAndView.addObject("error_message", "Incorrect password. Please try again!");
                    return modelAndView;
                }
            } else {
                System.out.println("ZZZZZZZZZZZZZZZZZZZZ user dont exist - plz sign up");
                ModelAndView modelAndView = new ModelAndView("home");
                modelAndView.addObject("error_message", "User don't exist. Check your email or sign up.");
                return modelAndView;
            }
    }
}
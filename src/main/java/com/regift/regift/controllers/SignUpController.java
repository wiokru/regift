package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public ModelAndView signup(Model model) {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView registerUser(Model model,
                                     HttpServletResponse response,
                               @ModelAttribute("name") String name,
                               @ModelAttribute("surname") String surname,
                               @ModelAttribute("email") String email,
                               @ModelAttribute("city") String city,
                               @ModelAttribute("password") String password) {
        try {
            User user = new User(email, name, surname, password, city);
            userRepository.save(user);
            return new ModelAndView("redirect:/");
//            response.sendRedirect("/");
        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX" + e.getMessage() + '\n' + e.getLocalizedMessage());
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }
}
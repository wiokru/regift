package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public String registerUser(Map<String, Object> model,
                        @ModelAttribute("name") String name,
                        @ModelAttribute("surname") String surname,
                        @ModelAttribute("email") String email,
                        @ModelAttribute("city") String city,
                        @ModelAttribute("passowrd") String password) {
        try {
            User user = new User(email, name, surname, password, city);
            userRepository.save(user);
            return "login";
        }
        catch (Exception e){
            model.put("message", e.getMessage());
            System.out.println("XXXXXXXXXXXXXXXXXXXXXX" + e.getMessage() + '\n' + e.getLocalizedMessage());
            return "error";
        }
    }
}
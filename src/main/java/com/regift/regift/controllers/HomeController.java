package com.regift.regift.controllers;

import com.regift.regift.utils.Const;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
                    LOGGER.setLevel(Level.INFO);
                    LOGGER.info(result.get(0).getEmail() + Const.LOGIN_SUCCESS_LOG);

                    return new ModelAndView("redirect:/user/" + result.get(0).getId().toString() + "/home");
                }
                else {
                    //PASSWORD INCORRECT
                    LOGGER.setLevel(Level.INFO);
                    LOGGER.info(result.get(0).getEmail() + Const.WRONG_PASSWORD_LOG);
                    ModelAndView modelAndView = new ModelAndView("home");
                    modelAndView.addObject("error_message", Const.INCORRECT_PASSWORD);
                    return modelAndView;
                }
            }
            else {
                LOGGER.setLevel(Level.INFO);
                LOGGER.info(email + Const.USER_DONT_EXISTS_LOG);
                ModelAndView modelAndView = new ModelAndView("home");
                modelAndView.addObject("error_message", Const.USER_DONT_EXISTS);
                return modelAndView;
            }
    }
}
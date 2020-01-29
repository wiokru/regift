package com.regift.regift.controllers;

import com.regift.regift.utils.Const;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserBuilder;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SignUpController {

    @Autowired
    private UserRepository userRepository;
    private UserBuilder userBuilder;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            this.userBuilder = new UserBuilder();
            User user = userBuilder
                    .setName(name)
                    .setSurname(surname)
                    .setEmail(email)
                    .setCity(city)
                    .setPassword(password)
                    .build();

            userRepository.save(user);

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.SAVED_NEW_USER_LOG);

            return new ModelAndView("redirect:/");
        }
        catch (Exception e) {
            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.REGISTRATION_ERROR + e.getMessage());

            ModelAndView modelAndView = new ModelAndView("signup");
            modelAndView.addObject("error_message", Const.SIGNUP_ERROR_MESSAGE);
            return modelAndView;
        }
    }
}
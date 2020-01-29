package com.regift.regift.controllers;

import com.regift.regift.utils.PostRepository;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class UserHomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/user/{id}/home")
    public ModelAndView userHomePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_home");
        Optional<User> currentUser = userRepository.findById(id);
        modelAndView.addObject("postList", postRepository.findAll());
        modelAndView.addObject("currentUser", currentUser.get());
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_posts")
    public ModelAndView userPostPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_posts");
        Optional<User> currentUser = userRepository.findById(id);

        modelAndView.addObject("postList", postRepository.findByUser(currentUser.get()));
        modelAndView.addObject("currentUser", currentUser.get());
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_info")
    public ModelAndView userInfoPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_info");
        Optional<User> currentUser = userRepository.findById(id);

        modelAndView.addObject("currentUser", currentUser.get());
        return modelAndView;
    }

    @PostMapping("/user/{id}/my_info")
    public ModelAndView saveEditUserInfo(@PathVariable("id") Long id,
                                         @ModelAttribute("name") String name,
                                         @ModelAttribute("surname") String surname,
                                         @ModelAttribute("email") String email,
                                         @ModelAttribute("city") String city) {
        User currentUser = userRepository.findById(id).get();
        try {
            currentUser.setName(name);
            currentUser.setSurname(surname);
            currentUser.setEmail(email);
            currentUser.setCity(city);

            userRepository.save(currentUser);
            ModelAndView modelAndView = new ModelAndView("user_info");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.TRUE);
            modelAndView.addObject("message", "User updated successfully.");
            return modelAndView;
        }
        catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("user_info");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.FALSE);
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }
}

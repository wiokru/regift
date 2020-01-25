package com.regift.regift.controllers;

import com.regift.regift.utils.PostRepository;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
        modelAndView.addObject("postList", postRepository.findAll());

        Optional<User> currentUser = userRepository.findById(id);
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
}

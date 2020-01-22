package com.regift.regift.controllers;

import com.regift.regift.utils.PostRepository;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserHomeController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PostRepository postRepository;
//
//    @GetMapping("/user/{id}/home")
//    public ModelAndView userHomePage(@PathVariable String id) {
//        return new ModelAndView("user_home");
//    }
}

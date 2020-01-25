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

    private User currentUser;

    @GetMapping("/user/{id}/home")
    public ModelAndView userHomePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_home");
        modelAndView.addObject("postList", postRepository.findAll());

        this.currentUser = userRepository.findById(id).get();
        modelAndView.addObject("currentUser", this.currentUser);
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_posts")
    public ModelAndView userPostPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_posts");
//        Optional<User> currentUser = userRepository.findById(id);

        modelAndView.addObject("postList", postRepository.findByUser(currentUser));
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_info")
    public ModelAndView userInfoPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_info");
//        Optional<User> currentUser = userRepository.findById(id);

        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @PostMapping("/user/{id}/my_info")
    public ModelAndView saveEditUserInfo(@PathVariable("id") Long id,
                                         @ModelAttribute("name") String name,
                                         @ModelAttribute("surname") String surname,
                                         @ModelAttribute("email") String email,
                                         @ModelAttribute("city") String city) {
        try {
            currentUser.setName(name);
            currentUser.setSurname(surname);
            currentUser.setEmail(email);
            currentUser.setCity(city);

            userRepository.save(currentUser);
            ModelAndView modelAndView = new ModelAndView("redirect:/user/" + id.toString() + "/my_info");
            modelAndView.addObject("info_message", "User updated successfully.");
            return modelAndView;
        }
        catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/user/" + id.toString() + "/my_info");
            modelAndView.addObject("error_message", e.getMessage());
            return modelAndView;
        }
    }
}

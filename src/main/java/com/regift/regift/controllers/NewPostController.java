package com.regift.regift.controllers;

import com.regift.regift.utils.Post;
import com.regift.regift.utils.PostRepository;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class NewPostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private User currentUser;

    @GetMapping("/user/{id}/my_posts/add_post")
    public ModelAndView addNewPost(@PathVariable("id") Long id) {
        this.currentUser = userRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("new_post_form");
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @PostMapping("/user/{id}/my_posts/add_post")
    public ModelAndView addNewPost(@PathVariable("id") Long id,
                                   @ModelAttribute("title") String title,
                                   @ModelAttribute("description") String description) {

        try{
            Post post = new Post(title, description, this.currentUser);
            postRepository.save(post);

            ModelAndView modelAndView = new ModelAndView("user_posts");
            modelAndView.addObject("postList", postRepository.findByUser(currentUser));
            modelAndView.addObject("currentUser", currentUser);
            return modelAndView;
        }
        catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("user_posts");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("error_message", e.getMessage());
            return modelAndView;
        }
    }
}

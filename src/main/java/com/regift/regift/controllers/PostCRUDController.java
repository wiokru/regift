package com.regift.regift.controllers;

import com.regift.regift.utils.Post;
import com.regift.regift.utils.PostRepository;
import com.regift.regift.utils.User;
import com.regift.regift.utils.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PostCRUDController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/user/{id}/my_posts/add_post")
    public ModelAndView addNewPost(@PathVariable("id") Long id) {
        User currentUser = userRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("new_post_form");
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @PostMapping("/user/{id}/my_posts/add_post")
    public ModelAndView addNewPost(@PathVariable("id") Long id,
                                   @ModelAttribute("title") String title,
                                   @ModelAttribute("description") String description) {

        User currentUser = userRepository.findById(id).get();
        try {
            Post post = new Post(title, description, currentUser);
            postRepository.save(post);

            ModelAndView modelAndView = new ModelAndView("user_posts");
            modelAndView.addObject("postList", postRepository.findByUser(currentUser));
            modelAndView.addObject("currentUser", currentUser);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("user_posts");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("error_message", e.getMessage());
            return modelAndView;
        }
    }

    @GetMapping("/user/{id}/my_posts/delete/{post_id}")
    public ModelAndView confirmDeletePost(@PathVariable("id") Long id,
                                          @PathVariable("post_id") Long postId) {
        User currentUser = userRepository.findById(id).get();
        Post post = postRepository.findById(postId).get();

        ModelAndView modelAndView = new ModelAndView("post_delete_confirm");
        modelAndView.addObject("currentPost", post);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @PostMapping("/user/{id}/my_posts/delete/{post_id}")
    public ModelAndView deletePost(@PathVariable("id") Long id,
                                   @PathVariable("post_id") Long postId) {
        User currentUser = userRepository.findById(id).get();
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);

        ModelAndView modelAndView = new ModelAndView("user_posts");
        modelAndView.addObject("postList", postRepository.findByUser(currentUser));
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_posts/edit/{post_id}")
    public ModelAndView editPost(@PathVariable("id") Long id,
                                 @PathVariable("post_id") Long postId) {
        User currentUser = userRepository.findById(id).get();
        Post post = postRepository.findById(postId).get();

        ModelAndView modelAndView = new ModelAndView("post_edit_form");
        modelAndView.addObject("currentPost", post);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }

    @PostMapping("/user/{id}/my_posts/edit/{post_id}")
    public ModelAndView saveEditPost(@PathVariable("id") Long id,
                                     @PathVariable("post_id") Long postId,
                                     @ModelAttribute("title") String title,
                                     @ModelAttribute("description") String description) {
        User currentUser = userRepository.findById(id).get();
        Post post = postRepository.findById(postId).get();

        try {
            post.setTitle(title);
            post.setDescription(description);

            postRepository.save(post);

            ModelAndView modelAndView = new ModelAndView("post_edit_form");
            modelAndView.addObject("currentPost", post);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.TRUE);
            modelAndView.addObject("message", "Post updated successfully.");
            return modelAndView;
        }
        catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("post_edit_form");
            modelAndView.addObject("currentPost", post);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.FALSE);
            modelAndView.addObject("message", e.getMessage());
            return modelAndView;
        }
    }
}

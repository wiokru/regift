package com.regift.regift.controllers;

import com.regift.regift.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PostCRUDController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


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

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.POST_ADDED_LOG);

            ModelAndView modelAndView = new ModelAndView("user_posts");

            List<Post> posts = postRepository.findByUser(currentUser).stream()
                    .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                    .collect(Collectors.toList());

            LOGGER.info(Const.USER_POST_SIZE + posts.size());

            modelAndView.addObject("postList", posts);
            modelAndView.addObject("currentUser", currentUser);
            return modelAndView;
        }
        catch (Exception e) {

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.SAVING_POST_ERROR_LOG + e.getMessage());

            ModelAndView modelAndView = new ModelAndView("new_post_form");
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

        LOGGER.setLevel(Level.INFO);
        LOGGER.info(Const.POST_DELETED_LOG);

        ModelAndView modelAndView = new ModelAndView("user_posts");

        List<Post> posts = postRepository.findByUser(currentUser).stream()
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());

        LOGGER.info(Const.USER_POST_SIZE + posts.size());

        modelAndView.addObject("postList", posts);
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

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.POST_UPDATED_LOG);

            ModelAndView modelAndView = new ModelAndView("post_edit_form");
            modelAndView.addObject("currentPost", post);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.TRUE);
            modelAndView.addObject("message", Const.POST_UPDATED_SUCCESS);
            return modelAndView;
        }
        catch (Exception e) {

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(Const.POST_UPDATED_ERROR_LOG + e.getMessage());

            ModelAndView modelAndView = new ModelAndView("post_edit_form");
            modelAndView.addObject("currentPost", post);
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.FALSE);
            modelAndView.addObject("message", Const.POST_UPDATED_ERROR + e.getMessage());
            return modelAndView;
        }
    }
}

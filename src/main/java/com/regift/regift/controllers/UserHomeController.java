package com.regift.regift.controllers;

import com.regift.regift.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class UserHomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @GetMapping("/user/{id}/home")
    public ModelAndView userHomePage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_home");
        Optional<User> currentUser = userRepository.findById(id);

        List<Post> posts = postRepository.findAll().stream()
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());

        LOGGER.setLevel(Level.INFO);
        LOGGER.info(Const.ALL_POST_SIZE + posts.size());

        modelAndView.addObject("postList", posts);
        modelAndView.addObject("currentUser", currentUser.get());
        return modelAndView;
    }

    @GetMapping("/user/{id}/my_posts")
    public ModelAndView userPostPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("user_posts");
        Optional<User> currentUser = userRepository.findById(id);

        List<Post> posts = postRepository.findByUser(currentUser.get()).stream()
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());

        LOGGER.setLevel(Level.INFO);
        LOGGER.info(Const.USER_POST_SIZE + posts.size());

        modelAndView.addObject("postList", posts);
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

            LOGGER.setLevel(Level.INFO);
            LOGGER.info(email + Const.USER_UPDATED_SUCCESS_LOG);

            ModelAndView modelAndView = new ModelAndView("user_info");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.TRUE);
            modelAndView.addObject("message", Const.USER_UPDATED_SUCCESS);
            return modelAndView;
        }
        catch (Exception e) {
            LOGGER.setLevel(Level.INFO);
            LOGGER.info(email + Const.USER_UPDATED_ERROR_LOG + e.getMessage());

            ModelAndView modelAndView = new ModelAndView("user_info");
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("is_success", Boolean.FALSE);
            modelAndView.addObject("message", Const.USER_UPDATED_ERROR + e.getMessage());
            return modelAndView;
        }
    }

    @PostMapping("/user/{id}/home")
    public ModelAndView searchPostByCity(@PathVariable("id") Long id,
                                          @ModelAttribute("city") String city) {

        ModelAndView modelAndView = new ModelAndView("user_home");
        Optional<User> currentUser = userRepository.findById(id);

        List<Post> posts = postRepository.findAll().stream()
                .filter(post -> post.getUser().getCity().toUpperCase().equals(city.toUpperCase()))
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());

        LOGGER.setLevel(Level.INFO);
        LOGGER.info(Const.ALL_POST_SIZE + posts.size());

        modelAndView.addObject("postList", posts);
        modelAndView.addObject("currentUser", currentUser.get());
        return modelAndView;
    }
}

package com.regift.regift.controllers;

import com.regift.regift.utils.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@RestController
public class SignUpController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/signup")
    public ModelAndView signup(Model model) {
        model.addAttribute("tempUser", new User());
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    String registerUser(Map<String, Object> model,
                        @ModelAttribute("name") String name,
                        @ModelAttribute("surname") String surname,
                        @ModelAttribute("email") String email,
                        @ModelAttribute("city") String city,
                        @ModelAttribute("passowrd") String password) {
        try (Connection connection = dataSource.getConnection()) {
            User user = new User(email, name, surname, password, city);

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users");
            stmt.executeUpdate("INSERT INTO users VALUES user");

            return "login";
        } catch (Exception e) {
            model.put("message", e.getMessage());
            return "error";
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }

//    public void signUp(@ModelAttribute("name") String name){
//
//    }
}
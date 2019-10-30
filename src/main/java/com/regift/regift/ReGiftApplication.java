package com.regift.regift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
@SpringBootApplication
public class ReGiftApplication {

	@Value("${spring.datasource.url}")
	private String dbUrl;

//	@Autowired
//	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(ReGiftApplication.class, args);
	}

	@RequestMapping("/")
	String index() {
		return "index";
	}

}

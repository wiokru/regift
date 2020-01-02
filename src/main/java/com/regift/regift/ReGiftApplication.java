package com.regift.regift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class ReGiftApplication {

//	@Value("${spring.datasource.url}")
//	private String dbUrl;

//	@Autowired
//	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(ReGiftApplication.class, args);
	}
}

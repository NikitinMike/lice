package com.digt.lice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@MapperScan("com.developer.project.mapper")
//@ComponentScan({"com.digt.lice.service"})
@EntityScan("com.digt.lice.model")
@EnableJpaRepositories("com.digt.lice.repositories")
public class LiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LiceApplication.class, args);
	}
}

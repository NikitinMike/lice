package com.digt.lice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@MapperScan("com.developer.project.mapper")
//@ComponentScan({"com.digt.lice.service"})
@EntityScan("com.digt.lice.model")
@EnableJpaRepositories("com.digt.lice.repositories")
public class LiceApplication extends SpringBootServletInitializer {
//	@Bean
//	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
//	webServerFactoryCustomizer() {
//		return factory -> factory.setContextPath("/license");
//	}
	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/license");
		SpringApplication.run(LiceApplication.class, args);
	}
}

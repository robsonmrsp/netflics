package com.robsonmrsp.netflics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.robsonmrsp.netflics")
@EntityScan({ "com.robsonmrsp.netflics.model", "com.robsonmrsp.netflics.core.model" })
@EnableJpaRepositories("com.robsonmrsp.netflics")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
} 
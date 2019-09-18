package com.sogeti.api.filmland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is the entry point which sets up Spring Boot application.
 * 
 * @SpringBootApplication annotation enables auto-configuration and component
 *                        scanning.

 * @author Rakesh
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class FilmLandApplication {

	/**
	 * Start point of execution
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FilmLandApplication.class, args);
	}
}
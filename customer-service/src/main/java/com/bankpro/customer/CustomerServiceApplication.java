package com.bankpro.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Customer Service application.
 * <p>
 * This class initializes the Spring Boot application context and starts the embedded server.
 * It serves as the bootstrap class for the microservice handling customer registration,
 * authentication, and profile management.
 * </p>
 *
 * @author Aniket Kamlesh
 */
@SpringBootApplication
public class CustomerServiceApplication {

	/**
	 * Main method that acts as the application’s entry point.
	 * It delegates to Spring Boot’s {@link SpringApplication#run(Class, String...)} method.
	 *
	 * @param args command-line arguments passed during startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
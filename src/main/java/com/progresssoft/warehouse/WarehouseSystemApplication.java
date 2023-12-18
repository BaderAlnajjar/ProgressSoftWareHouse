package com.progresssoft.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Warehouse System Application.
 * This class contains the main method to launch the Spring Boot application.
 *
 * @see SpringApplication
 */
@SpringBootApplication
public class WarehouseSystemApplication {

    /**
     * Main method to start the Warehouse System Application.
     *
     * @param args Command-line arguments passed to the application.
     * @see SpringApplication#run(Class, String...)
     */
    public static void main(String[] args) {
        SpringApplication.run(WarehouseSystemApplication.class, args);
    }

}

package com.project.bookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@PropertySource("file:.env")
public class BookmanagementApplication {
	@Autowired
	private static Dotenv dotenv;

	public static void main(String[] args) {
		dotenv=Dotenv.configure().load();
		// System.out.println("Database url "+dotenv.get("DATABASE_URL"));
		SpringApplication.run(BookmanagementApplication.class, args);
	}

}

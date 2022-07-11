package myfirstspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFirstSpringApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MyFirstSpringApplication.class);
		app.run(args);
	}
}

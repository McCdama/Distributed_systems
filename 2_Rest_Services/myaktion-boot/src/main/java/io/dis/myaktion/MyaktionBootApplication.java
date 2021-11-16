package io.dis.myaktion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyaktionBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyaktionBootApplication.class, args);
	}

	// Command-Line RunnerBean
	@Bean
	CommandLineRunner cerateSampleDate(){
		return (args) -> {
			System.out.println("Here is the place where we will create our sample data");
		} ;
	}

}

package com.kani.oams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class OnlineAyurvedaMedicineSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineAyurvedaMedicineSystemApplication.class, args);
	}

}

package com.fariz.carrental;

import com.fariz.carrental.messages.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.fariz.carrental.controller,com.fariz.carrental.services,com.fariz.carrental.model,com.fariz.carrental.dto,com.fariz.carrental.messages"})
public class CarrentalApplication {

	public static void main(String[] args) {
		Message.createMessage();
		SpringApplication.run(CarrentalApplication.class, args);
	}

}

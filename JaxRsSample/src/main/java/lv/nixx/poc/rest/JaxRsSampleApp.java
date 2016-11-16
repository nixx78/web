package lv.nixx.poc.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class JaxRsSampleApp {

	public static void main(String[] args) {
		SpringApplication.run(JaxRsSampleApp.class, args);
	}

}
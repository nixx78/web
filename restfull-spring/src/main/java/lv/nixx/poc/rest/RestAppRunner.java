package lv.nixx.poc.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//TODO https://www.baeldung.com/spring-boot-bean-validation
//TODO File upload + OpenDoc file choice button

@SpringBootApplication
@EnableScheduling
public class RestAppRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
		System.setProperty("catalina.base", ".");
        SpringApplication.run(RestAppRunner.class, args);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestAppRunner.class);
	}

}
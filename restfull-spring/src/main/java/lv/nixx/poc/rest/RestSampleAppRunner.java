package lv.nixx.poc.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class RestSampleAppRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestSampleAppRunner.class, args);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestSampleAppRunner.class);
	}
	
}
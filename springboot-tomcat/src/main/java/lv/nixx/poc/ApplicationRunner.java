package lv.nixx.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class ApplicationRunner extends SpringBootServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        System.setProperty("spring.application.json", "{\"prop1.name\":\"prop1.value\"}");
        super.onStartup(servletContext);
    }

    public static void main(String[] args) {
        System.setProperty("spring.application.json", "{\"prop1.name\":\"prop1.value\"}");
        SpringApplication.run(ApplicationRunner.class, args);
    }

}

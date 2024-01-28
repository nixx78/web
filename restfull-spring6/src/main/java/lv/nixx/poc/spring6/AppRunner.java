package lv.nixx.poc.spring6;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lv.nixx.poc.common.db.AlphaDB;
import lv.nixx.poc.common.db.BetaDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring 6 Rest POC", version = "2.0", description = "Spring Rest POC project"))
@EnableScheduling

@AlphaDB
@BetaDB
public class AppRunner {

    //TODO Review, how is possible to work with properties: https://www.baeldung.com/configuration-properties-in-spring-boot: "5. Using @ConfigurationProperties on a @Bean Method"

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }

}
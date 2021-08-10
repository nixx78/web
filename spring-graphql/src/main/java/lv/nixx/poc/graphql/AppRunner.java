package lv.nixx.poc.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRunner {

    //TODO https://thorben-janssen.com/dont-expose-entities-in-api/

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }

}


package lv.nixx.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class InfoController {

    private static final Logger log = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private Environment env;

    @Value("${prop1.name}")
    private String property;

    @GetMapping("/info")
    public String printInfo() {
        Date date = new Date();

        log.info("Response: {}", date);
        return "Hello, time: " + date + " current profile: " + Arrays.toString(env.getActiveProfiles()) + " property: " + property;
    }
}
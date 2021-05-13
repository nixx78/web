package lv.nixx.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

@RestController
public class InfoController {

    @Autowired
    private Environment env;

    @GetMapping("/info")
    public String printInfo() {
        return "Hello, time: " + new Date() + " current profile: " + Arrays.toString(env.getActiveProfiles());
    }
}
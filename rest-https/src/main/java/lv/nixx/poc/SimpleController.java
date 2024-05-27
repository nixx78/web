package lv.nixx.poc;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/info")
    public String process() {
        return "Info:" + System.currentTimeMillis();
    }

    @PostMapping(value = "/process", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String process(@RequestBody String body) {
        return "Processed:" + System.currentTimeMillis() + ":" + body;
    }


}

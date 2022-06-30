package lv.nixx.poc;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/public/hello1")
    public String hello1() {
        return "Hello1:" + System.currentTimeMillis();
    }

    @GetMapping("/public/hello2")
    public String hello2() {
        return "Hello2:" + System.currentTimeMillis();
    }

    @Hidden
    @GetMapping("/notVisibleInSwaggerEndpoint")
    public String hiddenEndpoint() {
        return "NotVisibleInSwaggerEndpoint:" + System.currentTimeMillis();
    }

    @GetMapping("/hidden/hidden1")
    public String hidden1() {
        return "Hidden1:" + System.currentTimeMillis();
    }

    @GetMapping("/hidden/hidden2")
    public String hidden2() {
        return "Hidden2:" + System.currentTimeMillis();
    }

}

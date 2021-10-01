package lv.nixx.poc.rest.controller;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final Map<String, String> customerDetails = Map.of(
            "cid1", "Details Name1, Surname1",
            "cid2", "Details Name2, Surname2"
    );

    @GetMapping("/login")
    public String login(@RequestParam(name = "customerId") String customerId, @ApiIgnore HttpSession session) {
        session.setAttribute("customerId", customerId);
        return "Customer:" + customerId + " login success";
    }

    @GetMapping("/customerDetails")
    public String getCustomerDetails(@ApiIgnore HttpSession session) {
        String customerId = (String) session.getAttribute("customerId");
        if (customerId == null) {
            return "Customer not logged in";
        }
        return customerDetails.getOrDefault(customerId, "Customer [" + customerId + "] not exists");
    }

}

package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {

        LOG.info("Login request come....");

        return "Success";
    }

}

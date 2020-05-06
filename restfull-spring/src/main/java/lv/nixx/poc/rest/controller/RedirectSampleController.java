package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

// https://developer.mozilla.org/en-US/docs/Web/HTTP/Redirections

@RestController
public class RedirectSampleController {

    private static final Logger LOG = LoggerFactory.getLogger(RedirectSampleController.class);

    @GetMapping("/serviceWithRedirect/{param}")
    public ResponseEntity<String> getBodyAndRedirect(@PathVariable String param, UriComponentsBuilder builder) {

        URI redirect = builder.path("redirect/{param}/{newParam}").buildAndExpand(param, System.currentTimeMillis()).toUri();
        LOG.info("Redirected to [{}]", redirect);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirect);

        return new ResponseEntity<>("Response: " + param, headers, HttpStatus.FOUND);
    }

    @GetMapping("redirect/{p1}/{p2}")
    public String redirect(@PathVariable String p1, @PathVariable long p2) {
        LOG.info("Redirect come, parameters p1 [{}] p2 [{}]", p1, p2);
        return "Response from redirected page: " + p1 + " : " + p2;
    }

    //TODO create redirect method with changed HTTP Method and different response objects



}

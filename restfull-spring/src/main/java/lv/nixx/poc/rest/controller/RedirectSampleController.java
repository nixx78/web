package lv.nixx.poc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;


//TODO Move to new project
@RestController
public class RedirectSampleController {

    private static final Logger LOG = LoggerFactory.getLogger(RedirectSampleController.class);

    // Redirect to external domain sample
    @GetMapping("/delfiRedirect")
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "https://www.delfi.lv");
        httpServletResponse.setStatus(302);
    }

    @GetMapping("/getServiceWithRedirect/{param}")
    public ResponseEntity<String> getBodyAndRedirect(@PathVariable String param, UriComponentsBuilder builder) {

        URI redirect = builder.path("redirect/{param}/{newParam}").buildAndExpand(param, System.currentTimeMillis()).toUri();
        LOG.info("Redirected to [{}]", redirect);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirect);

        return new ResponseEntity<>("Response: " + param, headers, HttpStatus.FOUND);
    }

    @PostMapping(value = "/postServiceWithRedirect/{param}")
    public ResponseEntity<String> postRedirect(@PathVariable String param,
                                               @RequestBody(required = false) String body,
                                               UriComponentsBuilder builder) {

        URI redirect = builder.path("redirectPost").build().toUri();
        LOG.info("POST request, body [{}] redirected to post [{}]", body, redirect);

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirect);

        // If status is PERMANENT_REDIRECT, redirect method not changed to GET. Body not changed, to redirect URL
        // original body will be passed
        return new ResponseEntity<>(body + ":" + param, headers, HttpStatus.PERMANENT_REDIRECT);
    }

    @GetMapping("/redirect/{p1}/{p2}")
    public String redirect(@PathVariable String p1, @PathVariable long p2) {
        LOG.info("GET Redirect come, parameters p1 [{}] p2 [{}]", p1, p2);
        return "Response from GET redirected page: " + p1 + " : " + p2;
    }

    @PostMapping(value = "/redirectPost")
    public String redirectPost(@RequestBody(required = false) String body) {
        // Body in case of REDIRECT will be null
        LOG.info("POST redirect come, body: [{}]", body);
        return "Response from POST redirected page:" + body;
    }


}

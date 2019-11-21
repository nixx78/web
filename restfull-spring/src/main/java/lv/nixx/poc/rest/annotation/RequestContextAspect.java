package lv.nixx.poc.rest.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Aspect
@Service
public class RequestContextAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestContextAspect.class);

    private HttpSession httpSession;

    @Autowired
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Before(value = "execution(* lv.nixx.poc.rest.controller.*.*(..)) && @annotation(requestDescriptor)")
    public void audit(RequestDescriptor requestDescriptor) {
        log.info("RequestDescriptor Aspect called [{}]", requestDescriptor);

        httpSession.setAttribute("action", requestDescriptor.action());
        httpSession.setAttribute("entity", requestDescriptor.entity());
    }

}

package lv.nixx.poc.exh.handler.annotation;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Aspect
@Service
public class RequestContextAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestContextAspect.class);

    @Before(value = "execution(* *.*(..)) && @annotation(requestDescriptor)")
    public void requestContext(RequestDescriptor requestDescriptor) {
        log.info("RequestDescriptor Aspect called [{}]", requestDescriptor);

        saveAttrib("action", requestDescriptor.action());
        saveAttrib("entity", requestDescriptor.entity());
    }

    @Before(value = "execution(* *.*(..)) && @annotation(apiDescription)")
    public void apiDescriptor(ApiOperation apiDescription) {
        log.info("ApiDescription Aspect called [{}]", apiDescription);

        saveAttrib("description", apiDescription.value());
    }


    private void saveAttrib(String name, String value) {
        ServletRequestAttributes r = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        r.setAttribute(name, value, SCOPE_REQUEST);
    }

}

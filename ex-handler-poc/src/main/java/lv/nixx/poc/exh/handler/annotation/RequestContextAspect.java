package lv.nixx.poc.exh.handler.annotation;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class RequestContextAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestContextAspect.class);

    @Before(value = "execution(* *.*(..)) && @annotation(descriptor)")
    public void requestContext(Descriptor descriptor) {
        log.info("RequestDescriptor Aspect called [{}]", descriptor);

        MDC.put("action", descriptor.action());
        MDC.put("entity", descriptor.entity());
    }

    @Before(value = "execution(* *.*(..)) && @annotation(apiDescription)")
    public void apiDescriptor(ApiOperation apiDescription) {
        log.info("ApiDescription Aspect called [{}]", apiDescription);

        MDC.put("description", apiDescription.value());
    }

}

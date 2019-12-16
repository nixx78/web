package lv.nixx.poc.rest.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduledExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledExceptionHandler.class);

    @AfterThrowing(pointcut="execution(* *(..)) && @annotation(lv.nixx.poc.rest.annotation.GlobalHandler)", throwing="ex")
    public void handleTheException(JoinPoint joinPoint, Exception ex) throws NoSuchMethodException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();

        GlobalHandler globalHandler = joinPoint.getTarget().getClass().getMethod(methodName,parameterTypes).getAnnotation(GlobalHandler.class);
        LOG.error("schedulerExceptionHandler, handle [{}] description [{}]", ex.getMessage(), globalHandler.description());
    }

}

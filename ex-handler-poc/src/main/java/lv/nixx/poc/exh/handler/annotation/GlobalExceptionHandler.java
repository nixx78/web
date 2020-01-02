package lv.nixx.poc.exh.handler.annotation;

import lv.nixx.poc.exh.handler.NotificationMarker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Pointcut("within(@lv.nixx.poc.exh.handler.annotation.Descriptor *)")
    public void classAnnotatedWithDescriptor() {}

    @Pointcut("@annotation(lv.nixx.poc.exh.handler.annotation.Descriptor)")
    public void methodsAnnotatedWithDescriptor() {}

    @AfterThrowing(pointcut="classAnnotatedWithDescriptor() || methodsAnnotatedWithDescriptor()", throwing="ex")
    public void handleTheException(JoinPoint joinPoint, Exception ex) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();

        final Class<?> aClass = joinPoint.getTarget().getClass();

        final Optional<Descriptor> methodAnnotation = Optional.ofNullable(aClass.getMethod(methodName, parameterTypes).getAnnotation(Descriptor.class));
        final Optional<Descriptor> classAnnotation = Optional.ofNullable(aClass.getAnnotation(Descriptor.class));

        final String d = methodAnnotation.map(Descriptor::description).orElse("");
        final String classDesc = classAnnotation.map(Descriptor::description).orElse("");

        final String action = methodAnnotation.map(Descriptor::action).orElse("");
        final String classAction = classAnnotation.map(Descriptor::action).orElse("");

        final String entity = methodAnnotation.map(Descriptor::entity).orElse("");
        final String classEntity = classAnnotation.map(Descriptor::entity).orElse("");

        MDC.put("description", String.join("#", classDesc, d));
        MDC.put("action", String.join("#", classAction, action));
        MDC.put("entity", String.join("#", classEntity, entity));
        MDC.put("exception", ex.getMessage());

        LOG.error(NotificationMarker.get(), "Global exception handler, handle [{}] description [{}]", ex.getMessage(), String.join("#", classDesc, d));
    }


}

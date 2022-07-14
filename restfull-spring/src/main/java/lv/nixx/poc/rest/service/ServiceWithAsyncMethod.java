package lv.nixx.poc.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ServiceWithAsyncMethod {

    private static final Logger log = LoggerFactory.getLogger(ServiceWithAsyncMethod.class);

    @Async("asyncExecutor")
    public void asyncMethod(String requestId) {
        log.info("Method, id [{}] start and sleep...", requestId);
        try {
            TimeUnit.SECONDS.sleep(10);
            log.info("Method completed, id [{}]", requestId);
        } catch (InterruptedException e) {
            log.error("Error during async method call", e);
        }

    }
}

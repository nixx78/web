package lv.nixx.poc.rest.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceWithAsyncMethod {

    private static final Logger log = LoggerFactory.getLogger(ServiceWithAsyncMethod.class);

    private final DataProvider alphaDataProvider = new DataProvider("Alpha", 100);
    private final DataProvider betaDataProvider = new DataProvider("Beta", 200);
    private final DataProvider gammaDataProvider = new DataProvider("Gamma", 500);

    @Async("asyncExecutor")
    public void asyncMethod(String requestId) {
        log.info("Method, id [{}] start and sleep...", requestId);
        sleep(10_000);
        log.info("Method completed, id [{}]", requestId);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Collection<String>> getAlphaData(String requestId) {
        return alphaDataProvider.process(requestId);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Collection<String>> getBetaData(String requestId) {
        return betaDataProvider.process(requestId);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Collection<String>> getGammaData(String requestId) {
        return gammaDataProvider.process(requestId);
    }

    private void sleep(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error("Error during async method call", e);
        }
    }

    @AllArgsConstructor
    class DataProvider {
        private final String entity;
        private final int sleepTime;

        @Async("asyncExecutor")
        CompletableFuture<Collection<String>> process(String requestId) {
            long stTime = System.currentTimeMillis();
            List<String> value = List.of(
                    entity + "1." + requestId,
                    entity + "2." + requestId,
                    entity + "3." + requestId);
            sleep(sleepTime);

            log.info("Request for entity [{}] requestId [{}] completed, time [{}]", entity, requestId, (System.currentTimeMillis() - stTime));

            return CompletableFuture.completedFuture(value);
        }

    }


}

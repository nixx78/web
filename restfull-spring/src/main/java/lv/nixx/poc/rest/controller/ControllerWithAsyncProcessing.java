package lv.nixx.poc.rest.controller;

import lv.nixx.poc.rest.service.ServiceWithAsyncMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class ControllerWithAsyncProcessing {

    private static final Logger log = LoggerFactory.getLogger(ControllerWithAsyncProcessing.class);

    private final ServiceWithAsyncMethod service;

    @Autowired
    public ControllerWithAsyncProcessing(ServiceWithAsyncMethod service) {
        this.service = service;
    }

    @GetMapping("/asyncEndpoint")
    public String asyncEndpoint(@RequestParam String requestId) {
        service.asyncMethod(requestId);
        return String.format("completed, id [%s] : %s", requestId, System.currentTimeMillis());
    }

    @GetMapping("/asyncEndpointWithResponse")
    public Map<String, Object> process(@RequestParam String requestId) {

        long stTime = System.currentTimeMillis();

        // Async метод по умолчанию должен вызываться из разных классов

        CompletableFuture<Collection<String>> alphaData = service.getAlphaData(requestId);
        CompletableFuture<Collection<String>> betaData = service.getBetaData(requestId);
        CompletableFuture<Collection<String>> gammaData = service.getGammaData(requestId);

        CompletableFuture.allOf(alphaData, betaData, gammaData).join();

        long processingTime = System.currentTimeMillis() - stTime;
        log.info("Request [{}] total processing time [{}] milliseconds", requestId, processingTime);

        try {
            return Map.of(
                    "alphaData", alphaData.get(),
                    "betaData", betaData.get(),
                    "gammaData", gammaData.get(),
                    "processingTime", processingTime
            );
        } catch (InterruptedException | ExecutionException ex) {
            throw new IllegalStateException(ex);
        }

    }

}

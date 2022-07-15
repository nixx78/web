package lv.nixx.poc.rest.configuration;

import lv.nixx.poc.rest.service.Component1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AppConfiguration {

    private Component1 component1;

    @Autowired
    public void setComponent1(Component1 component1) {
        this.component1 = component1;
    }

    @Bean
    public CommonsRequestLoggingFilter getCommonsRequestLoggingFilter() {

        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1000);
        filter.setIncludeHeaders(false);
        filter.setBeforeMessagePrefix(" Receive request: ");
        filter.setAfterMessagePrefix("Send response: ");

        return filter;
    }

    @Bean
    public HealthIndicator comp1HealthIndicator() {
        return () -> Health.status(Status.UP)
                .withDetail("detail1", "text1")
                .withDetail("message", component1.getStatusMessage())
                .build();
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }

}

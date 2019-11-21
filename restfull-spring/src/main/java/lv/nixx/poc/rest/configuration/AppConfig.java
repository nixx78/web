package lv.nixx.poc.rest.configuration;

import lv.nixx.poc.rest.service.Component1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

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
		filter.setAfterMessagePrefix("Request received: ");

		return filter;
	}

	@Bean
	public HealthIndicator comp1HealthIndicator() {
		return () -> Health.status(Status.UP)
				.withDetail("detail1", "text1")
				.withDetail("message", component1.getStatusMessage())
				.build();
	}

}

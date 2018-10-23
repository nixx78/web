package lv.nixx.poc.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class AppConfig {

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

}

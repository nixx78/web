package lv.nixx.poc.sync2asynch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {
	
	@Bean
	public ThreadPoolTaskExecutor executor() {
		 ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	     executor.setCorePoolSize(7);
	     executor.setMaxPoolSize(42);
	     executor.setQueueCapacity(11);
	     executor.setThreadNamePrefix("ConsumerThread");
	     executor.initialize();
	     return executor;
	}

}

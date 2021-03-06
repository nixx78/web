package lv.nixx.poc.sync2async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLifesycleListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	ExternalSystemQueuePooler consumerService;
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		taskExecutor.execute(consumerService);
	}

}

package lv.nixx.poc.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class ScheduledService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron="*/5 * * * * SUN-FRI")
	@Scheduled(cron="*/17 * * * * SUN-FRI")
	public void fireScheduler() {
		log.info("Fire event [{}]", new Date());
	}
	

}

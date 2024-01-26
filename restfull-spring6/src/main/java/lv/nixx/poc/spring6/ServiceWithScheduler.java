package lv.nixx.poc.spring6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithScheduler {

    private static final Logger log = LoggerFactory.getLogger(ServiceWithScheduler.class);

    @Scheduled(cron = "0 15/1 6-11 * * *")
    public void fire() {
        log.info("=========== Fire ==============");
    }

}

package lv.nixx.poc.exh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTask {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(fixedDelay = 10_000)
    public void scheduledTask() {
        LOG.error("Exception in scheduler task");
//        throw new SchedulerException("Exception in scheduler task");
    }

}

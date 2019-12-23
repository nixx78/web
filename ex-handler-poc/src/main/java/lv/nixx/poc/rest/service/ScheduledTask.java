package lv.nixx.poc.rest.service;

import lv.nixx.poc.rest.annotation.GlobalHandler;
import lv.nixx.poc.rest.exception.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTask {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    //@Scheduled(fixedDelay = 10_000)
    @GlobalHandler(description = "Task description")
    public void scheduledTask() {
        LOG.error("Exception in scheduler task");
//        throw new SchedulerException("Exception in scheduler task");
    }

}

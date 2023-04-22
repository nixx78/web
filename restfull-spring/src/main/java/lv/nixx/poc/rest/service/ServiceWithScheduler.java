package lv.nixx.poc.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithScheduler {

    private static final Logger log = LoggerFactory.getLogger(ServiceWithScheduler.class);

    // Каждую вторую минуту и 15 секудну
    @Scheduled(cron = "15 */2 * * * *")
    public void fireScheduler2And15() {
        log.info("Scheduler fired: \"15 */2 * * * *\" ");

    }

    // Через 40 секунд и каждую первую минуту
    @Scheduled(cron = "*/40 */1 * * * *")
    public void fireScheduler1And40() {
        log.info("Scheduler fired: \"*/40 */1 * * * *\"");
    }

}

package lv.nixx.poc.exh;

import lv.nixx.poc.exh.handler.NotificationMarker;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggerSandbox {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerSandbox.class);

    @Test
    public void test() {

        MDC.put("action", "action.value");

        LOG.error("Simple error message1");
        LOG.error(NotificationMarker.notification(), "Notification message");
    }

}

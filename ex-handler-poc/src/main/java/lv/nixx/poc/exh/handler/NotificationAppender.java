package lv.nixx.poc.exh.handler;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.Map;

public class NotificationAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent event) {
        final Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();

        System.out.println("NA" + event + "\n\t Map: " + mdcPropertyMap);


    }

}

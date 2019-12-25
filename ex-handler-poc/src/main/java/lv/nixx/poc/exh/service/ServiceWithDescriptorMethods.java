package lv.nixx.poc.exh.service;

import lv.nixx.poc.exh.handler.NotificationMarker;
import lv.nixx.poc.exh.handler.annotation.Descriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithDescriptorMethods {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceWithDescriptorMethods.class);

    @Descriptor(action = "notification.without.exception", description = "Notify user just using LOG")
    public void process() {
        LOG.info("Process method");
        LOG.error(NotificationMarker.get(), "Error notification to user");
    }

    @Descriptor(action = "global.exception.handling.samle", description = "Throw exception from method, global catch")
    public void processWithException() {
        throw new IllegalStateException("Exception inside service");
    }


}

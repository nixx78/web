package lv.nixx.poc.exh.service;

import lv.nixx.poc.exh.handler.annotation.Descriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Descriptor(description = "Description for class")
public class ServiceWithTypeDescriptor {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceWithTypeDescriptor.class);

    public void processWithException() {
        LOG.info("Process method");
        throw new IllegalStateException("Exception in: ServiceWithTypeDescriptor");
    }

}

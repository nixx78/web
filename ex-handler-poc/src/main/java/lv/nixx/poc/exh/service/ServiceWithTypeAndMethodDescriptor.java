package lv.nixx.poc.exh.service;

import lv.nixx.poc.exh.handler.annotation.Descriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Descriptor(description = "Description for class", entity = "Entity description type level", action = "Action description")
public class ServiceWithTypeAndMethodDescriptor {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceWithTypeAndMethodDescriptor.class);

    @Descriptor(description = "Description for method", entity = "Entity description method level", action = "Action method level description")
    public void processWithException() {
        LOG.info("Process method");
        throw new IllegalStateException("Exception in: ServiceWithTypeDescriptor");
    }

}

package lv.nixx.poc.exh.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceWithDescriptorMethodsTest {

    @Autowired
    private ServiceWithDescriptorMethods service;

    @Test
    public void processTest() {
        service.process();
    }

    @Test
    public void processWithExceptionTest() {
        Assertions.assertThrows(IllegalStateException.class, service::processWithException);
    }

}

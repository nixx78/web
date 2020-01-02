package lv.nixx.poc.exh.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceWithTypeMethodDescriptorTest {

    @Autowired
    private ServiceWithTypeAndMethodDescriptor service;

    @Test
    public void processWithExceptionTest() {
        Assertions.assertThrows(IllegalStateException.class, service::processWithException);
    }

}

package lv.nixx.poc.react.restfull;

import org.junit.jupiter.api.Test;
import reactor.netty.http.client.HttpClient;

class HttpClientSample {

    @Test
    void callHello() {
        HttpClient client = HttpClient.create();

        String helloResponse = client.get()
                .uri("http://localhost:8080/hello")
                .responseSingle((r, bytes) -> bytes.asString())
                .block();

        System.out.println("HelloResponse:" + helloResponse);

        String messageResponse = client.get()
                .uri("http://localhost:8080/message/test_message")
                .responseSingle((r, bytes) -> bytes.asString())
                .block();

        System.out.println("MessageResponse:" + messageResponse);
    }

}

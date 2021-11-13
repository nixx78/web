package lv.nixx.poc.react.sandbox;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO https://www.baeldung.com/java-9-reactive-streams
// TODO https://habr.com/ru/post/565050/
public class JavaConcurrentPublisherSubscriber {

    @Test
    public void publishSubscribeTest() throws InterruptedException {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        publisher.subscribe(new EndSubscriber<>());
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        assertEquals(1, publisher.getNumberOfSubscribers());
        items.forEach(publisher::submit);
        publisher.close();

        Thread.currentThread().join(500);
    }

    @Test
    public void publishSubscribeWithTransformation() throws InterruptedException {

        // publisher -> TransformProcessor -> EndSubscriber

        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        TransformProcessor<Integer, String> transformProcessor = new TransformProcessor<>(t -> "Processed:" + t);
        publisher.subscribe(transformProcessor);

        EndSubscriber<String> subscriber = new EndSubscriber<>();
        transformProcessor.subscribe(subscriber);

        List<Integer> data = List.of(1, 2, 3);
        data.forEach(publisher::submit);
        publisher.close();

        Thread.currentThread().join(500);
    }

}

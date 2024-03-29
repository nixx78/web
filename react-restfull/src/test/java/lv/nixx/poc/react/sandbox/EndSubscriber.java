package lv.nixx.poc.react.sandbox;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;

public class EndSubscriber<T> implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
        System.out.println("Subscription created");
    }

    @Override
    public void onNext(T item) {
        System.out.println(Thread.currentThread().getName() + ": onNext() : " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}

package lv.nixx.poc.sync2async.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.stereotype.Component;

import lv.nixx.poc.sync2async.domain.RequestResponse;

@Component
public class QueueProvider {
	
	private BlockingQueue<RequestResponse> queue = new ArrayBlockingQueue<>(100);
	
	public void addToQueue(RequestResponse request) {
		queue.offer(request);
	}
	
	public RequestResponse take() throws InterruptedException {
		return queue.take();
	}
	
	public int size() {
		return queue.size();
	}

}

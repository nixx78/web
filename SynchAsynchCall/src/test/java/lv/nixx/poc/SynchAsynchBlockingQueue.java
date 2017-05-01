package lv.nixx.poc;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lv.nixx.poc.sync2async.domain.RequestResponse;

public class SynchAsynchBlockingQueue {

	AtomicInteger idProvider = new AtomicInteger(0);

	@Test
	public void executeProducerConsumer() throws InterruptedException {
		BlockingQueue<RequestResponse> bq = new ArrayBlockingQueue<>(10, true);

		Consumer consumer = new Consumer(bq);
		
		Arrays.asList(
				new RequestProducer(bq, "1"),
				new RequestProducer(bq, "2"),
				new RequestProducer(bq, "3")
				).forEach(RequestProducer::start);

		consumer.start();
		consumer.join();
	}

	class RequestProducer extends Thread {
		private static final long TIMEOUT = 5000;
		BlockingQueue<RequestResponse> bq;
		String threadPostfix;

		RequestProducer(BlockingQueue<RequestResponse> bq, String threadPostfix) {
			super("ProducerThread." + threadPostfix);
			this.bq = bq;
			this.threadPostfix = threadPostfix;
		}

		@Override
		public void run() {
			Thread currentThread = Thread.currentThread();
			while(!currentThread.isInterrupted()) {
				int id = idProvider.getAndIncrement();
				RequestResponse r = new RequestResponse(id, "req." + System.currentTimeMillis());
				if ( bq.offer(r) ) {
					System.out.println("Thread [" + currentThread.getName() + "] send [" + r + "]");
					synchronized (r) {
						try {
							r.wait(TIMEOUT);
							
							System.out.println("Thread [" + currentThread.getName() + "] response [" + r + "]");
						} catch (InterruptedException e) {
							currentThread.interrupt();
						}
					}
				} else {
					System.out.println("Request for ID [" + id + "] can't be processed, queue is full");
				}
			}
		}

	}

	class Consumer extends Thread {

		BlockingQueue<RequestResponse> bq;

		public Consumer(BlockingQueue<RequestResponse> bq) {
			super("ConsumerThread");
			this.bq = bq;
		}

		@Override
		public void run() {
			Thread currentThread = Thread.currentThread();
			while (!currentThread.isInterrupted()) {
				try {
					TimeUnit.SECONDS.sleep(1);
					RequestResponse r = bq.take();
					System.out.println("Thread [" + currentThread.getName() + "] processed [" + r + "]");
					synchronized (r) {
						r.response = r.request + ".resp";
						r.notify();
					}
				} catch (InterruptedException e) {
					currentThread.interrupt();
				}
			}
		}

	}

}

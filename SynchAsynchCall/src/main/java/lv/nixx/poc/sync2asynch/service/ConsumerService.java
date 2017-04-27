package lv.nixx.poc.sync2asynch.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService implements Runnable {

	Logger LOG = LoggerFactory.getLogger(ConsumerService.class);

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		while (!currentThread.isInterrupted()) {
			LOG.info("Tic-tac");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				currentThread.interrupt();
			}
		}
	}

}

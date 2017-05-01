package lv.nixx.poc.sync2async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.nixx.poc.sync2async.domain.RequestResponse;

@Component
public class ExternalSystemQueuePooler implements Runnable {
	
	@Autowired
	QueueProvider queueProvider;

	Logger LOG = LoggerFactory.getLogger(ExternalSystemQueuePooler.class);

	@Override
	public void run() {
		LOG.info("Pooler thread is started");
		Thread currentThread = Thread.currentThread();
		while (!currentThread.isInterrupted()) {
			try {
				RequestResponse r = queueProvider.take();
				synchronized (r) {
					r.response = r.request + ":resp";
					
					LOG.info("For id [{}] response is created", r.id);
					r.notify();
				}
			} catch (InterruptedException e) {
				currentThread.interrupt();
			}
		}
	}

}

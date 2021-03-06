package lv.nixx.poc.sync2async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.nixx.poc.sync2async.domain.RequestResponse;

@Component
public class ExternalSystemQueuePooler implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(ExternalSystemQueuePooler.class);

	@Autowired
	QueueProvider queueProvider;
	
	@Autowired
	ExternalSystem externalSystem;

	@Override
	public void run() {
		log.info("Pooler thread is started");
		Thread currentThread = Thread.currentThread();
		while (!currentThread.isInterrupted()) {
			try {
				RequestResponse r = queueProvider.take();
				synchronized (r) {
					r.response = externalSystem.processRequest(r.request); 
					log.info("For id [{}] response is created", r.id);
					r.notify();
				}
			} catch (InterruptedException e) {
				log.info("Shutdown request received, queue size [{}]", queueProvider.size());
				currentThread.interrupt();
			}
		}
	}

}

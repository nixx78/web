package lv.nixx.poc.sync2async.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lv.nixx.poc.sync2async.domain.RequestResponse;

@Component
public class ExternalSystemService {
	
	private static final long TIMEOUT = 5_000;
	private static AtomicInteger idProvider = new AtomicInteger(0);
	
	private static final Logger LOG = LoggerFactory.getLogger(ExternalSystemService.class);
	
	@Autowired
	private QueueProvider queueProvider;
	
	public String processRequest(String request) throws InterruptedException {
		
		RequestResponse requestResponse = new RequestResponse(idProvider.incrementAndGet(), request);
		queueProvider.addToQueue(requestResponse);
		
		synchronized (requestResponse) {
			requestResponse.wait(TIMEOUT);
		}
		
		LOG.info("Response come [{}]", requestResponse);
		
		return requestResponse.response;
	}
	

}

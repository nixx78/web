package lv.nixx.poc.sync2async.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalSystem {

	private static final Logger log = LoggerFactory.getLogger(ExternalSystem.class);

	public String processRequest(String request) {
		log.debug("Request to external system come [{}]", request);

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return request + ":resp:" + System.currentTimeMillis();
	}

}

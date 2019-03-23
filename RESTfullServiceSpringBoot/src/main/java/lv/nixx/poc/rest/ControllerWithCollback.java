package lv.nixx.poc.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sandbox")
public class ControllerWithCollback {
	
	private static final Logger LOG = LoggerFactory.getLogger(ControllerWithCollback.class);
	
	private int port = 8080;

	@RequestMapping(method=RequestMethod.GET, value="/synchrequest/{id}")
	public @ResponseBody String request(@PathVariable(name="id") String id) throws URISyntaxException {
		LOG.info("Request with id: [{}]", id );
		
		sendAsynchResponse(id);

		return "synch:response:" + id + "#" + System.currentTimeMillis();
	}

	@RequestMapping(path="/asynchresponse", method=RequestMethod.POST)
	public void callback(@RequestBody String request) {
		LOG.info("asynch response [{}]", request);
	}
	
	private void sendAsynchResponse(String id) {
		new Thread(new AsynchResponse(id)).start();
	}
	
	class AsynchResponse implements Runnable {

		private String id;
		public AsynchResponse(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(5);
				URI uri = new URI("http://localhost:" + port + "/sandbox/asynchresponse");
				HttpEntity<String> entity = new HttpEntity<>("asynch_response_to_request:" + id);
				
				LOG.info("Send asynch response to URI [{}]", uri);
				new RestTemplate().exchange(uri, HttpMethod.POST, entity, String.class);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		
	}

	
}

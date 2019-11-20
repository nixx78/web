package lv.nixx.poc.rest.controller;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sandbox")
public class ControllerWithCallback {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerWithCallback.class);

	private final int port = 8080;

	@GetMapping(value="/synchrequest/{id}")
	public @ResponseBody String request(@PathVariable(name="id") String id)  {
		LOG.info("Request with id: [{}]", id );

		sendAsynchResponse(id);

		return "synch:response:" + id + "#" + System.currentTimeMillis();
	}

	@PostMapping("/asynchresponse")
	public void callback(@RequestBody String request) {
		LOG.info("asynch response [{}]", request);
	}

	private void sendAsynchResponse(String id) {
		new Thread(new AsynchResponse(id)).start();
	}

	class AsynchResponse implements Runnable {

		private final String id;
		AsynchResponse(String id) {
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

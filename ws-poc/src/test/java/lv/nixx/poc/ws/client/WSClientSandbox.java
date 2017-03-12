package lv.nixx.poc.ws.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class WSClientSandbox {
	
	@Test
	public void callWSUsingPost() throws URISyntaxException {
		
		RestTemplate rt = new RestTemplate();
		
		
		String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
					"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
						"<soap12:Body>" +
							"<ConversionRate xmlns=\"http://www.webserviceX.NET/\">" + 
								"<FromCurrency>USD</FromCurrency>" +
								"<ToCurrency>EUR</ToCurrency>" +
							"</ConversionRate>" +
						"</soap12:Body>" +
					"</soap12:Envelope>";

      	MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/xml");
		headers.add("charset", "utf-8");
		headers.add("SOAPAction", "http://www.webserviceX.NET/ConversionRate");
		
		HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
      
		URI url = new URI("http://www.webservicex.net/CurrencyConvertor.asmx");

		ResponseEntity<String> exchange = rt.exchange(url, HttpMethod.POST, requestEntity, String.class);
		
		System.out.println(exchange.getBody());
		
	}

}

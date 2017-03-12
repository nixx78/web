package lv.nixx.poc.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import lv.nixx.poc.wsdl.currency.*;

@Service
public class CurrencyService {
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public Double getConversionRate(Currency fromCurrency, Currency toCurrency) {
		ConversionRate conversionRate = new ObjectFactory().createConversionRate();
		conversionRate.setFromCurrency(fromCurrency);
		conversionRate.setToCurrency(toCurrency);
		ConversionRateResponse response = (ConversionRateResponse) webServiceTemplate.marshalSendAndReceive(conversionRate);

		return response.getConversionRateResult();
	}
	
	
}
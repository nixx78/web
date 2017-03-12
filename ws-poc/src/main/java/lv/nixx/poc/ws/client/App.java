package lv.nixx.poc.ws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lv.nixx.poc.wsdl.currency.Currency;

public class App {

	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		CurrencyService currencyService = context.getBean(CurrencyService.class);

		Currency fromCurrency = Currency.USD;
		Currency toCurrency = Currency.GBP;
		Double conversionRate = currencyService.getConversionRate(fromCurrency, toCurrency);

		log.info(String.format("The conversion rate from %s to %s is %s.", fromCurrency, toCurrency, conversionRate));
		
	}
}

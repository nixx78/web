package lv.nixx.poc.ws.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Configuration
@ComponentScan(basePackages = "lv.nixx.poc.ws.client")
public class ApplicationConfig {
	
	@Bean
	public SaajSoapMessageFactory soapMessageFactory() {
		SaajSoapMessageFactory f = new SaajSoapMessageFactory();
		f.setSoapVersion(SoapVersion.SOAP_12);
		return f;
	}
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller m = new Jaxb2Marshaller();
		m.setContextPath("lv.nixx.poc.wsdl.currency");
		return m;
	}
	
	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate wst = new WebServiceTemplate(soapMessageFactory());
		wst.setMarshaller(marshaller());
		wst.setUnmarshaller(marshaller());
		wst.setDefaultUri("http://www.webservicex.net/CurrencyConvertor.asmx?WSDL");
		return wst;
	}
}

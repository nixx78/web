package lv.nixx.poc.sync2async.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExternalSystemRestController {
	
	@Autowired
	ExternalSystemFacade externalSystemFacade;
	
	@RequestMapping(method=RequestMethod.GET, value="externalCall/{req}", produces="text/plain")
	public String externalSystemCall(@PathVariable("req") String req) throws InterruptedException{
		return externalSystemFacade.processRequestInQueue(req);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="externalAsynchCall/{req}", produces="text/plain")
	public String externalAsynchCall(@PathVariable("req") String req) throws Exception{
		CompletableFuture<String> cf = externalSystemFacade.processRequestAsync(req);
		return cf.get();
	}


}

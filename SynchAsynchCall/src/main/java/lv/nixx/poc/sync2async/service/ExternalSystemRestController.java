package lv.nixx.poc.sync2async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ExternalSystemRestController {
	
	@Autowired
	ExternalSystemService externalSystem;
	
	@RequestMapping(method=RequestMethod.GET, value="externalCall/{req}", produces="text/plain")
	public String externalSystemCall(@PathVariable("req") String req) throws InterruptedException{
		return externalSystem.processRequest(req);
	}

}

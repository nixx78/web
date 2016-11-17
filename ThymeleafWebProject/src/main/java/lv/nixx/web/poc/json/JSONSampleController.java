package lv.nixx.web.poc.json;

import java.util.*;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JSONSampleController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PersonFormObject formObject = new PersonFormObject();
	private Map<String, Person> persons = new TreeMap<>();

	public JSONSampleController() {
		persons.put("code1", new Person("code1", "PName1", "SName1"));
		persons.put("code2", new Person("code2", "PName2", "SName2"));
		persons.put("code3", new Person("code3", "PName3", "SName3"));
		persons.put("code4", new Person("code4", "PName4", "SName4"));
		persons.put("code5", new Person("code5", "PName5", "SName5"));
		
		formObject.setSelectedCode("code5");
		formObject.setPersonCodes(new ArrayList<>(persons.keySet()));
	}

	@RequestMapping(value = "/jsonsample", method = RequestMethod.GET)
	public ModelAndView initialPage() {
		ModelAndView model = new ModelAndView();

		model.addObject("formObject", formObject);
		return model;
	}
	
	@RequestMapping(value = "/jsonsample", method = RequestMethod.POST)
	public void post(@ModelAttribute("formObject") PersonFormObject fo) {
		final String selectedCode = fo.getSelectedCode();
		log.info("info from page: code [{}] name [{}] surname [{}]", selectedCode, fo.getName(), fo.getSurname() );
		
		Person person = persons.get(selectedCode);
		person.setName(fo.getName());
		person.setSurname(fo.getSurname());
		
		// this value is null, we need to set this value again
		fo.setPersonCodes(new ArrayList<>(persons.keySet()));
	}

	@RequestMapping(value = "/json/request", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String personInfoRequest(@RequestParam String code) {
		Person p = persons.get(code);
		String json = "{\"name\":\"" +  p.getName() +"\",\"surname\":\"" + p.getSurname() +"\"}";

		log.info("request for code [{}] JSON [{}]", code, json);
		return json;
	}

}

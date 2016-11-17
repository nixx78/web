package lv.nixx.web.poc.dropdown;

import java.util.*;

import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DropdownController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final Map<String, String> codeMapping = new HashMap<>();

	public DropdownController() {
		codeMapping.put("code1", "codeValueMapping1");
		codeMapping.put("code2", "codeValueMapping2");
		codeMapping.put("code3", "codeValueMapping3");
	}

	@RequestMapping(value = "/dropdown", method = RequestMethod.GET)
	public ModelAndView initialPage() {
		ModelAndView model = new ModelAndView();
		FormObject formObject = new FormObject();
		formObject.setCurrentValue("code2");
		formObject.setCodeMapping("codeMapping2");

		model.addObject("formObject", formObject);
		return model;
	}

	@RequestMapping(value = "/dropdown/request", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
	@ResponseBody
	public String codeMappingRequest(@RequestParam String code) {
		if (codeMapping.containsKey(code)) {
			String mapping = codeMapping.get(code);
			log.info("for code [{}] mapping is [{}]", code, mapping);
			return mapping;
		}
		
		log.info("mapping for code not [{}] exists", code);
		return "UNDEFINED";
	}
	

}

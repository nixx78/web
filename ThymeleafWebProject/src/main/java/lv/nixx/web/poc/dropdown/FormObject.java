package lv.nixx.web.poc.dropdown;

import java.util.*;

public class FormObject {

	private String currentValue;
	private Map<String, String> values = new TreeMap<>();
	private String codeMapping;
	
	public FormObject(){
		values.put("code1", "Value1");
		values.put("code2", "Value2");
		values.put("code3", "Value3");
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String selectedValue) {
		this.currentValue = selectedValue;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public String getCodeMapping() {
		return codeMapping;
	}

	public void setCodeMapping(String codeMapping) {
		this.codeMapping = codeMapping;
	}

}

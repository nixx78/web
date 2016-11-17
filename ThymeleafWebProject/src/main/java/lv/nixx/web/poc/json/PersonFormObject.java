package lv.nixx.web.poc.json;

import java.util.List;

public class PersonFormObject {

	private String selectedCode;
	private List<String> personCodes;
	private String name;
	private String surname;

	public String getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}

	public List<String> getPersonCodes() {
		return personCodes;
	}

	public void setPersonCodes(List<String> personCodes) {
		this.personCodes = personCodes;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}

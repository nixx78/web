package lv.nixx.web.poc.json;

public class Person {

	private String code;
	private String name;
	private String surname;
	
	public Person(String code, String name, String surname) {
		this.code = code;
		this.name = name;
		this.surname = surname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

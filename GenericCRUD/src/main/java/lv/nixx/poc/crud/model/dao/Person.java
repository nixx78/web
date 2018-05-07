package lv.nixx.poc.crud.model.dao;

import java.util.Date;

import lombok.Data;

@Data
public class Person {

	private String id;
	private String name;
	private String surname;
	private Date dateOfBirth;


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + "]";
	}

}

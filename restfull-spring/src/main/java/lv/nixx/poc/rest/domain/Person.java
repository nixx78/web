package lv.nixx.poc.rest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ToString
@NoArgsConstructor
@Data
public class Person {

	private int id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	
	public Person(String name, String surname, Date dateOfBirth ){
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}


}

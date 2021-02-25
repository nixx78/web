package lv.nixx.poc.rest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ToString
@NoArgsConstructor
@Data
@Accessors(chain = true)
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

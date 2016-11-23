package lv.nixx.poc.rest;


public class PersonFixtures {

	
	public static String createPersonJSON(Integer id){
		return "{\"id\":\"" + id + "\",\"name\":\"person.name\",\"surname\":\"person.surname\",\"dateOfBirth\":1389707693735}";
	}
	
	public static String createPersonXML(Integer id){
		return "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><person id='" + id.toString() + "'><dateOfBirth>2014-01-15T18:08:02.249+02:00</dateOfBirth><name>new.person.name</name><surname>new.person.surname</surname></person>";
	}
	
}

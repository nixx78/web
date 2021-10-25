package lv.nixx.poc.rest;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonFixtures {

    public static String createPersonJSON(Integer id) throws ParseException {
        final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return "{\"id\":\"" + id + "\",\"name\":\"person.name\",\"surname\":\"person.surname\"," +
                "\"dateOfBirth\":" + df.parse("06.12.1978").getTime() + "}";
    }

    public static String createPersonXML(Integer id) {
        return "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><person id='" + id.toString() + "'><dateOfBirth>2014-01-15T18:08:02.249+02:00</dateOfBirth><name>new.person.name</name><surname>new.person.surname</surname></person>";
    }

}

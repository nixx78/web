package lv.nixx.poc.rest.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lv.nixx.poc.rest.validation.PersonAgeConstrain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

@XmlRootElement
@ToString
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PersonDTO {

    private int id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Minimum allowed 'name' is '3'")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, message = "Minimum allowed 'surname' is '3'")
    private String surname;

    @PersonAgeConstrain(minAge = 18)
    private Date dateOfBirth;

    public PersonDTO(String name, String surname, Date dateOfBirth ){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


}
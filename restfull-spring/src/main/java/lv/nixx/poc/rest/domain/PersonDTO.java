package lv.nixx.poc.rest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@ToString
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PersonDTO {

    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    private Date dateOfBirth;

    public PersonDTO(String name, String surname, Date dateOfBirth ){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


}
package lv.nixx.poc.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ToString
@Getter
@Accessors(chain = true)
public class PersonDTO {

    private final int id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "Minimum allowed 'name' is '3'")
    private final String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, message = "Minimum allowed 'surname' is '3'")
    private final String surname;

    private final LocalDateTime dateOfBirth;

    @JsonCreator
    public PersonDTO(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("dateOfBirth") LocalDateTime dateOfBirth
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

}
package lv.nixx.poc.crud.person.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.nixx.poc.crud.model.internal.BaseInternalModel;

@Data
@EqualsAndHashCode(callSuper= false)
public class PersonInternalModel extends BaseInternalModel<String> {

	public PersonInternalModel(String id) {
		super(id);
	}

	private String name;
	private String surname;
	private Date dateOfBirth;

	private String override1;
	private String override2;

	private String online1;
	private String online2;

}

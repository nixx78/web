package lv.nixx.poc.crud.person.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.nixx.poc.crud.model.rest.BaseResponse;

@Data
@EqualsAndHashCode(callSuper=false)
public class PersonResponse extends BaseResponse {
	private String id;
	private String nameSurname;
}

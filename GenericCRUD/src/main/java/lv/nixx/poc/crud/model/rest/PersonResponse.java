package lv.nixx.poc.crud.model.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PersonResponse extends BaseResponse {
	private String id;
	private String nameSurname;
}

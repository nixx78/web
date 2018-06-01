package lv.nixx.poc.crud.model.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlphaResponse extends BaseResponse {

	public String responseString;
	
}

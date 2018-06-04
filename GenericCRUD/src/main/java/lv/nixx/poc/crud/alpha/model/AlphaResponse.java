package lv.nixx.poc.crud.alpha.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.nixx.poc.crud.model.rest.BaseResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlphaResponse extends BaseResponse {

	public String responseString;
	
}

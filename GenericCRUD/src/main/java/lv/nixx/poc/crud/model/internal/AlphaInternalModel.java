package lv.nixx.poc.crud.model.internal;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlphaInternalModel extends BaseInternalModel<Integer> {

	public AlphaInternalModel(Integer id) {
		super(id);
	}

	public String firstAlphaValue;
	public String secondAlphaValue;
	private String stringHazelcastData;
	private BigDecimal bigDecimaHazelcastlData;

}

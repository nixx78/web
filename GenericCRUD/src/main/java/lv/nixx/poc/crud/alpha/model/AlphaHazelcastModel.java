package lv.nixx.poc.crud.alpha.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lv.nixx.poc.crud.model.hazelcast.BaseHazelcastModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlphaHazelcastModel extends BaseHazelcastModel<Integer> {
	
	private String stringData;
	private BigDecimal bigDecimalData;

}

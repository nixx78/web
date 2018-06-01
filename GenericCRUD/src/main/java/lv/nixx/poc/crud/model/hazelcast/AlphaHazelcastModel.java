package lv.nixx.poc.crud.model.hazelcast;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AlphaHazelcastModel extends BaseHazelcastModel<Integer> {
	
	private String stringData;
	private BigDecimal bigDecimalData;

}

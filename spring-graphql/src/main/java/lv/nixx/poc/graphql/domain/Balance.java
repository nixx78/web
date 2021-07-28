package lv.nixx.poc.graphql.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Balance {
    private BigDecimal amount;
    private String date;
}

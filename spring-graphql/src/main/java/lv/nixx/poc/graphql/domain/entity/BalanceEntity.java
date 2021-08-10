package lv.nixx.poc.graphql.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.nixx.poc.graphql.domain.Balance;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BalanceEntity implements Balance {
    private BigDecimal amount;
    private String date;
}

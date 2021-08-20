package lv.nixx.poc.graphql.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Balance {
    private final BigDecimal amount;
    private final String date;
}

package lv.nixx.poc.graphql.domain;

import java.math.BigDecimal;

public interface Balance {
    BigDecimal getAmount();
    String getDate();
}

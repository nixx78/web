package lv.nixx.poc.graphql.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import lv.nixx.poc.graphql.domain.Balance;

@Data
@Accessors(chain = true)
public class AccountEntity {
    private long id;
    private String name;
    private Balance balance;

    public AccountEntity(long id) {
        this.id = id;
    }
}

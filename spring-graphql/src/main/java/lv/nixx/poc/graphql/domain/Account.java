package lv.nixx.poc.graphql.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Account {

    private long id;
    private String name;
    private Balance balance;

    public Account(long id) {
        this.id = id;
    }

}

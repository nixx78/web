package lv.nixx.poc.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lv.nixx.poc.graphql.domain.Account;
import lv.nixx.poc.graphql.domain.Balance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    private static final Map<Long, Account> books = Map.of(
            10L, new Account(10L)
                    .setName("name10")
                    .setBalance(new Balance(BigDecimal.valueOf(100.20), "07/28/2021")),
            20L, new Account(20L)
                    .setName("name20")
                    .setBalance(new Balance(BigDecimal.valueOf(200.30), "07/27/2021")),
            30L, new Account(30L)
                    .setName("name30")
                    .setBalance(new Balance(BigDecimal.valueOf(300.40), "07/26/2021"))
    );

    public Account accountById(final long id) {
        return books.get(id);
    }

}

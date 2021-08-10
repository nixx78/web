package lv.nixx.poc.graphql;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.BalanceEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AccountDAO {

    private static final Map<Long, AccountEntity> accounts = Map.of(
            10L, new AccountEntity(10L)
                    .setName("name10")
                    .setBalance(new BalanceEntity(BigDecimal.valueOf(100.20), "07/28/2021")),
            20L, new AccountEntity(20L)
                    .setName("name20")
                    .setBalance(new BalanceEntity(BigDecimal.valueOf(200.30), "07/27/2021")),
            30L, new AccountEntity(30L)
                    .setName("name30")
                    .setBalance(new BalanceEntity(BigDecimal.valueOf(300.40), "07/26/2021"))
    );

    public AccountEntity getAccountById(long id) {
        return accounts.get(id);
    }

}

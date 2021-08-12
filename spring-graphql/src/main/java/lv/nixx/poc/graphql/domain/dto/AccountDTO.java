package lv.nixx.poc.graphql.domain.dto;

import lv.nixx.poc.graphql.domain.Account;
import lv.nixx.poc.graphql.domain.Balance;
import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.BalanceEntity;

public class AccountDTO implements Account {

    private final AccountEntity entity;

    public AccountDTO(AccountEntity entity) {
        this.entity = entity;
    }

    @Override
    public long getId() {
        return entity.getId();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public Balance getBalance() {
        BalanceEntity b = entity.getBalance();
        return new BalanceDTO(b.getAmount(), b.getDate());
    }
}

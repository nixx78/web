package lv.nixx.poc.graphql.domain.dto;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.AccountType;
import lv.nixx.poc.graphql.domain.entity.BalanceEntity;

public class Account {

    private final AccountEntity entity;

    public Account(AccountEntity entity) {
        this.entity = entity;
    }

    public long getId() {
        return entity.getId();
    }

    public String getNumber() {
        return entity.getNumber();
    }

    public Balance getBalance() {
        BalanceEntity b = entity.getBalance();
        return new Balance(b.getAmount(), b.getDate());
    }

    public Customer getCustomer() {
        return new Customer(entity.getCustomer());
    }

    public String getType() {
        AccountType type = entity.getType();
        return type == null ? null : type.name();
    }

}

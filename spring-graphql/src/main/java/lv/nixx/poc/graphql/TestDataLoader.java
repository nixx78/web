package lv.nixx.poc.graphql;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.BalanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestDataLoader {

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {

        accountRepository.save(new AccountEntity()
                .setName("name10")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(100.20), "07/28/2021")));

        accountRepository.save(new AccountEntity()
                .setName("name20")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(200.30), "07/27/2021")));

        accountRepository.save(new AccountEntity()
                .setName("name30")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(300.40), "07/26/2021")));
    }

}

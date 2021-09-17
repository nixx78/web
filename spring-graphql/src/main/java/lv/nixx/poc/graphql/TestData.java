package lv.nixx.poc.graphql;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import lv.nixx.poc.graphql.domain.entity.ApplicationEntity;
import lv.nixx.poc.graphql.domain.entity.BalanceEntity;
import lv.nixx.poc.graphql.domain.entity.CustomerEntity;
import lv.nixx.poc.graphql.repository.AccountRepository;
import lv.nixx.poc.graphql.repository.ApplicationRepository;
import lv.nixx.poc.graphql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestData {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public TestData(AccountRepository accountRepository, CustomerRepository customerRepository, ApplicationRepository applicationRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.applicationRepository = applicationRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void load() {

        CustomerEntity c1 = new CustomerEntity()
                .setName("name1")
                .setSurname("surname1")
                .setPCode("xxxx-yyyyy");

        customerRepository.save(c1);

        accountRepository.save(new AccountEntity()
                .setCustomer(c1)
                .setNumber("0010-100200-100")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(100.20), "07/28/2021")));

        accountRepository.save(new AccountEntity()
                .setCustomer(c1)
                .setNumber("0010-100200-101")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(200.30), "07/27/2021")));

        accountRepository.save(new AccountEntity()
                .setCustomer(c1)
                .setNumber("0010-100200-102")
                .setBalance(new BalanceEntity(BigDecimal.valueOf(300.40), "07/26/2021")));

        applicationRepository.save(new ApplicationEntity()
                .setCustomer(c1)
                .setText("Please close my card, number 1111-1111-1111"));

        applicationRepository.save(new ApplicationEntity()
                .setCustomer(c1)
                .setText("Please assign loan  1000Eur"));

    }

}

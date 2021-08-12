package lv.nixx.poc.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lv.nixx.poc.graphql.domain.Account;
import lv.nixx.poc.graphql.domain.dto.AccountDTO;
import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    private AccountRepository repo;

    @Autowired
    public void setRepo(AccountRepository accRepo) {
        this.repo = accRepo;
    }

    public Account accountById(final long id) {
        Optional<AccountEntity> byId = repo.findById(id);
        return new AccountDTO(byId.orElseThrow( () -> new IllegalArgumentException("Account with Id: " + id + " not exists")));
    }

    /*
    http://localhost:8080/graphiql

    {
  accountById(id: 10) {
    name
    balance {
      amount
      date
    }
  }
}
     */

}

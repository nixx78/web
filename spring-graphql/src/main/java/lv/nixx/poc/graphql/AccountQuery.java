package lv.nixx.poc.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lv.nixx.poc.graphql.domain.Account;
import lv.nixx.poc.graphql.domain.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    private AccountDAO dao;

    @Autowired
    public void setDao(AccountDAO dao) {
        this.dao = dao;
    }

    public Account accountById(final long id) {
        return new AccountDTO(dao.getAccountById(id));
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

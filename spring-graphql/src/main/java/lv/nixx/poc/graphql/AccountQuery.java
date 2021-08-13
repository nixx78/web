package lv.nixx.poc.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lv.nixx.poc.graphql.domain.Account;
import lv.nixx.poc.graphql.domain.dto.AccountDTO;
import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import java.util.Optional;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    private AccountRepository repo;

    @Autowired
    public void setRepo(AccountRepository accRepo) {
        this.repo = accRepo;
    }

    public Account accountById(final long id, DataFetchingEnvironment environment) {

        DataFetchingFieldSelectionSet fields = environment.getSelectionSet();

        Optional<AccountEntity> result;
        if (fields.contains("balance")) {
            result = repo.findOne((root, query, builder) -> {
                root.fetch("balance", JoinType.LEFT);
                return builder.equal(root.get("id"), id);
            });
        } else {
            result = repo.findById(id);
        }

        return new AccountDTO(result.orElseThrow(() -> new IllegalArgumentException("Account with Id: " + id + " not exists")));

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

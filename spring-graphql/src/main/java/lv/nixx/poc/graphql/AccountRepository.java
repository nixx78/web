package lv.nixx.poc.graphql;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {

}

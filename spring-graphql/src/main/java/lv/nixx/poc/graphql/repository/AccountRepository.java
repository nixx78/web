package lv.nixx.poc.graphql.repository;

import lv.nixx.poc.graphql.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {

}

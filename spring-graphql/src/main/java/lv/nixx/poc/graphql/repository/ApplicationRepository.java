package lv.nixx.poc.graphql.repository;

import lv.nixx.poc.graphql.domain.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long>,
        JpaSpecificationExecutor<ApplicationEntity> {
}

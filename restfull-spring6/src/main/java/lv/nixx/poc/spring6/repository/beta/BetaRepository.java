package lv.nixx.poc.spring6.repository.beta;

import lv.nixx.poc.spring6.orm.beta.BetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetaRepository extends JpaRepository<BetaEntity, Long> {
}

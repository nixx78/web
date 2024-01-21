package lv.nixx.poc.spring6.repository.alpha;

import lv.nixx.poc.spring6.orm.alpha.AlphaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlphaRepository extends JpaRepository<AlphaEntity, Long> {
}

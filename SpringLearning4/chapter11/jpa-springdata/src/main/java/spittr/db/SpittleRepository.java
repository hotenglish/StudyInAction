package spittr.db;

import org.springframework.data.jpa.repository.JpaRepository;
import spittr.domain.Spittle;

import java.util.List;

public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleRepositoryCustom {

    List<Spittle> findBySpitterId(long spitterId);

}

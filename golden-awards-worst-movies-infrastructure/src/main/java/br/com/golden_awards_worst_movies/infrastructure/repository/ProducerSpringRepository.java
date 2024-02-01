package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducerSpringRepository extends JpaRepository<ProducerEntity, Long> {

    List<ProducerEntity> findByNameIn(@Param("names") List<String> names);

}

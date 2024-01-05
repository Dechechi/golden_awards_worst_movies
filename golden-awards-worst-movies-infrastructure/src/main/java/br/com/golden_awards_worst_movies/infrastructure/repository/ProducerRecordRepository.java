package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRecordRepository extends JpaRepository<ProducerRecordEntity, Long> {

    Optional<ProducerRecordEntity> findByProducer(ProducerEntity producer);

}

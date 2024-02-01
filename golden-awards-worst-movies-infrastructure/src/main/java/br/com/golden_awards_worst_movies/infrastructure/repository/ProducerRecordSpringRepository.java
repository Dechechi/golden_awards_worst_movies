package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProducerRecordSpringRepository extends JpaRepository<ProducerRecordEntity, Long> {

    Optional<ProducerRecordEntity> findByFollowingWinAndPreviousWin(int followingWin, int previousWin);

    @Query("SELECT r FROM ProducerRecordEntity r WHERE r.intervalTime = (SELECT MAX(r2.intervalTime) FROM ProducerRecordEntity r2)")
    List<ProducerRecordEntity> findAllWithMaxInterval();

    @Query("SELECT r FROM ProducerRecordEntity r WHERE r.intervalTime = (SELECT MIN(r2.intervalTime) FROM ProducerRecordEntity r2 WHERE r2.intervalTime <> 0)")
    List<ProducerRecordEntity> findAllWithMinInterval();
    void deleteAllByProducer(ProducerEntity producer);
}
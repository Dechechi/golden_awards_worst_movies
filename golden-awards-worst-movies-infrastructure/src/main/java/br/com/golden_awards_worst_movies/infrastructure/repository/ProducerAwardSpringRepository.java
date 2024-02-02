package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerAwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProducerAwardSpringRepository extends JpaRepository<ProducerAwardEntity, Long> {

    Optional<ProducerAwardEntity> findByFollowingWinAndPreviousWin(int followingWin, int previousWin);

    @Query("SELECT r FROM ProducerAwardEntity r WHERE r.intervalTime = (SELECT MAX(r2.intervalTime) FROM ProducerAwardEntity r2)")
    List<ProducerAwardEntity> findAllWithMaxInterval();

    @Query("SELECT r FROM ProducerAwardEntity r WHERE r.intervalTime = (SELECT MIN(r2.intervalTime) FROM ProducerAwardEntity r2 WHERE r2.intervalTime <> 0)")
    List<ProducerAwardEntity> findAllWithMinInterval();
    @Modifying
    @Query("DELETE FROM ProducerAwardEntity r WHERE r.producer.id = :id")
    void deleteAllByProducerId(@Param("id") Long id);
}
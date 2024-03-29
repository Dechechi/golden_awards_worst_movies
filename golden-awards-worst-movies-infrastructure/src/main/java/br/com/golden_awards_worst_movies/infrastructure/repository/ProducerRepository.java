package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducerRepository extends JpaRepository<ProducerEntity, String> {

    List<ProducerEntity> findByNameIn(@Param("names") List<String> names);

//    @Query("SELECT p.name, m.id FROM ProducerEntity p JOIN p.movies m")
//    List<Object[]> findAllProducersWithMovieIds();

}

package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioSpringRepository extends JpaRepository<StudioEntity, Long> {

    List<StudioEntity> findByNameIn(@Param("names") List<String> names);

}

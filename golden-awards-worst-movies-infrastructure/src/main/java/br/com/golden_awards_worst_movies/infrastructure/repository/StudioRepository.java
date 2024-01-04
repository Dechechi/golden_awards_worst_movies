package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioRepository extends JpaRepository<StudioEntity, String> {

    List<StudioEntity> findByNameIn(@Param("names") List<String> names);

//    @Query("SELECT s.name, m.id FROM StudioEntity s JOIN s.movies m")
//    List<Object[]> findAllStudiosWithMovieIds();

}

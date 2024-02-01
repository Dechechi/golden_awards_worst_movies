package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSpringRepository extends JpaRepository<MovieEntity, Long> {
}

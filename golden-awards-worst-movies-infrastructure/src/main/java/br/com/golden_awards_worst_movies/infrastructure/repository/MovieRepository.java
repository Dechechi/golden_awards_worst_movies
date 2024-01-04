package br.com.golden_awards_worst_movies.infrastructure.repository;

import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}

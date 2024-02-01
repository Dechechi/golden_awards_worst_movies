package br.com.golden_awards_worst_movies.domain.repository;

import br.com.golden_awards_worst_movies.domain.model.Movie;

import java.util.List;

public interface MovieRepositoryI {

    Movie save(Movie movie);
    void delete(Long id);
    List<Movie> findAll();
    Movie findById(Long id);

}

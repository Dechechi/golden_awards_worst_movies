package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.repository.MovieSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRepository implements MovieRepositoryI {

    final MovieSpringRepository repository;

    @Autowired
    public MovieRepository(MovieSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Movie> findAll() {
        return null;
    }

    @Override
    public Movie findById(Long id) {
        return null;
    }
}

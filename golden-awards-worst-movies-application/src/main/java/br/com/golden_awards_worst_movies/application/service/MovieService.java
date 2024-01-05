package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.exception.MovieAlreadyExistsException;
import br.com.golden_awards_worst_movies.domain.exception.MovieDontExistException;
import br.com.golden_awards_worst_movies.domain.model.Movie;

import java.util.List;

public interface MovieService {

    Movie createMovie(Movie movie) throws MovieAlreadyExistsException;
    Movie updateMovie(Movie movie) throws MovieDontExistException, MovieAlreadyExistsException;
    void deleteMovie(Long id) throws MovieDontExistException;
    List<Movie> findAllMovies();
    Movie findMovie(Long id) throws MovieDontExistException;

}

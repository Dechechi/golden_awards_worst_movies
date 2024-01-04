package br.com.golden_awards_worst_movies.domain.mapper;

import br.com.golden_awards_worst_movies.domain.dto.MovieResponse;
import br.com.golden_awards_worst_movies.domain.model.Movie;

public class DomainToResponseMapper {

    public MovieResponse mapToMovieResponse(Movie movie){
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.id() != null ? movie.id() : null);
        movieResponse.setYear(movie.year());
        movieResponse.setProducers(movie.producers());
        movieResponse.setStudios(movie.studios());
        movieResponse.setTitle(movie.title());
        movieResponse.setWinner(movie.winner());
        return movieResponse;
    }

}

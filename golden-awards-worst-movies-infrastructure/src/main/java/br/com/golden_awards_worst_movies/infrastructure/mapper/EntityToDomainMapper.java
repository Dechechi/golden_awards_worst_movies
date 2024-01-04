package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDomainMapper {

    public Movie mapMovieEntityToDomain(MovieEntity movie){
        return new Movie(movie.getId(),
                movie.getReleaseYear(),
                movie.getTitle(),
                movie.getStudios().stream().map(this::mapStudioEntityToDomain).collect(Collectors.toList()),
                movie.getProducers().stream().map(this::mapProducerEntityToDomain).collect(Collectors.toList()),
                movie.isWinner());
    }

    public List<Movie> mapMovieEntityListToDomainList(List<MovieEntity> movieEntities){
        return movieEntities.stream().map(this::mapMovieEntityToDomain).collect(Collectors.toList());
    }

    public Studio mapStudioEntityToDomain(StudioEntity studio){
        return new Studio(studio.getName());
    }

    public Producer mapProducerEntityToDomain(ProducerEntity producer){
        return new Producer(producer.getName());
    }

}

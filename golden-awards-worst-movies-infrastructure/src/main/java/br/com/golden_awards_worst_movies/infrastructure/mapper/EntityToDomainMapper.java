package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.*;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerAwardEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDomainMapper {

    public Movie mapMovieEntityToDomain(MovieEntity movie){
        return new Movie.Builder().withId(movie.getId())
                .withYear(movie.getReleaseYear())
                .withTitle(movie.getTitle())
                .withStudios(movie.getStudios().stream().map(this::mapStudioEntityToDomain).collect(Collectors.toList()))
                .withProducers(movie.getProducers().stream().map(this::mapProducerEntityToDomain).collect(Collectors.toList()))
                .withWinner(movie.isWinner()).build();
    }

    public List<Movie> mapMovieEntityListToDomainList(List<MovieEntity> movieEntities){
        return movieEntities.stream().map(this::mapMovieEntityToDomain).collect(Collectors.toList());
    }

    public Studio mapStudioEntityToDomain(StudioEntity studio){
        return new Studio.Builder().withId(studio.getId()).withName(studio.getName()).build();
    }

    public Producer mapProducerEntityToDomain(ProducerEntity producer){
        return new Producer.Builder().withId(producer.getId())
                .withName(producer.getName())
                .withAwardYears(Arrays.stream(producer.getAwardYears().split(";"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList())).build();
    }

    public ProducerAward mapAwardEntityRecordToDomain(ProducerAwardEntity producerAwardEntity){
        return new ProducerAward.Builder().withId(producerAwardEntity.getId())
                .withProducer(mapProducerEntityToDomain(producerAwardEntity.getProducer()))
                .withInterval(producerAwardEntity.getIntervalTime())
                .withPreviousWin(producerAwardEntity.getPreviousWin())
                .withFollowingWin(producerAwardEntity.getFollowingWin()).build();
    }

}

package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.*;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerAwardEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.stream.Collectors;

public class DomainToEntityMapper {

    public MovieEntity mapNewMovie(Movie movie){
        MovieEntity movieEntity = new MovieEntity();
        if (movie.getId() != null) movieEntity.setId(movie.getId());
        movieEntity = mapMovie(movieEntity, movie);
        return movieEntity;
    }

    public MovieEntity mapMovie(MovieEntity entity, Movie movie){
        entity.setReleaseYear(movie.getYear());
        entity.setTitle(movie.getTitle());
        entity.setProducers(movie.getProducers().stream().map(producer -> mapProducer(new ProducerEntity(), producer)).collect(Collectors.toSet()));
        entity.setStudios(movie.getStudios().stream().map(this::mapNewStudio).collect(Collectors.toSet()));
        entity.setWinner(movie.isWinner());
        return entity;
    }

    public StudioEntity mapNewStudio(Studio studio){
        StudioEntity studioEntity = new StudioEntity();
        studioEntity = mapStudio(studioEntity, studio);
        return studioEntity;
    }

    public StudioEntity mapStudio(StudioEntity entity, Studio studio){
        entity.setName(studio.getName());
        return entity;
    }

    public ProducerEntity mapNewProducer(Producer producer){
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setId(producer.getId());
        producerEntity = mapProducer(producerEntity, producer);
        return producerEntity;
    }

    public ProducerEntity mapProducer(ProducerEntity entity, Producer producer){
        entity.setName(producer.getName());
        entity.setAwardYears(producer.getAwardYears().stream()
                .map(Object::toString)
                .collect(Collectors.joining(";")));
        return entity;
    }

    public ProducerAwardEntity mapNewAward(ProducerAward producerAward){
        ProducerAwardEntity producerAwardEntity = new ProducerAwardEntity();
        producerAwardEntity = mapAward(producerAwardEntity, producerAward);
        return producerAwardEntity;
    }

    public ProducerAwardEntity mapAward(ProducerAwardEntity entity, ProducerAward producerAward){
        entity.setProducer(mapNewProducer(producerAward.getProducer()));
        entity.setIntervalTime(producerAward.getInterval());
        entity.setPreviousWin(producerAward.getPreviousWin());
        entity.setFollowingWin(producerAward.getFollowingWin());
        return entity;
    }

}

package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.*;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.stream.Collectors;

public class DomainToEntityMapper {

    public MovieEntity mapMovieDomainToEntity(Movie movie){
        MovieEntity movieEntity = new MovieEntity();
        if (movie.id() != null) movieEntity.setId(movie.id());
        movieEntity.setReleaseYear(movie.year());
        movieEntity.setTitle(movie.title());
        movieEntity.setProducers(movie.producers().stream().map(this::mapProducerDomainToEntity).collect(Collectors.toSet()));
        movieEntity.setStudios(movie.studios().stream().map(this::mapStudioDomainToEntity).collect(Collectors.toSet()));
        movieEntity.setWinner(movie.winner());
        return movieEntity;
    }

    public StudioEntity mapStudioDomainToEntity(Studio studio){
        StudioEntity studioEntity = new StudioEntity();
        studioEntity.setName(studio.name());
        return studioEntity;
    }

    public ProducerEntity mapProducerDomainToEntity(Producer producer){
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setId(producer.id());
        producerEntity.setName(producer.name());
        producerEntity.setAwardYears(producer.awardYears().stream()
                .map(Object::toString)
                .collect(Collectors.joining(";")));
        return producerEntity;
    }

    public ProducerRecordEntity mapAwardDomainToEntity(ProducerAward producerAward){
        ProducerRecordEntity producerRecordEntity = new ProducerRecordEntity();
        producerRecordEntity.setProducer(mapProducerDomainToEntity(producerAward.producer()));
        producerRecordEntity.setIntervalTime(producerAward.interval());
        producerRecordEntity.setPreviousWin(producerAward.previousWin());
        producerRecordEntity.setFollowingWin(producerAward.followingWin());
        return producerRecordEntity;
    }

}

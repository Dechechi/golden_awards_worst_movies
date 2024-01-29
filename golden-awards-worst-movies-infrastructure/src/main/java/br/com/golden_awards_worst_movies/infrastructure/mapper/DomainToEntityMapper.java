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
        if (movie.getId() != null) movieEntity.setId(movie.getId());
        movieEntity.setReleaseYear(movie.getYear());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setProducers(movie.getProducers().stream().map(this::mapProducerDomainToEntity).collect(Collectors.toSet()));
        movieEntity.setStudios(movie.getStudios().stream().map(this::mapStudioDomainToEntity).collect(Collectors.toSet()));
        movieEntity.setWinner(movie.isWinner());
        return movieEntity;
    }

    public StudioEntity mapStudioDomainToEntity(Studio studio){
        StudioEntity studioEntity = new StudioEntity();
        studioEntity.setName(studio.getName());
        return studioEntity;
    }

    public ProducerEntity mapProducerDomainToEntity(Producer producer){
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setId(producer.getId());
        producerEntity.setName(producer.getName());
        producerEntity.setAwardYears(producer.getAwardYears().stream()
                .map(Object::toString)
                .collect(Collectors.joining(";")));
        return producerEntity;
    }

    public ProducerRecordEntity mapAwardDomainToEntity(ProducerAward producerAward){
        ProducerRecordEntity producerRecordEntity = new ProducerRecordEntity();
        producerRecordEntity.setProducer(mapProducerDomainToEntity(producerAward.getProducer()));
        producerRecordEntity.setIntervalTime(producerAward.getInterval());
        producerRecordEntity.setPreviousWin(producerAward.getPreviousWin());
        producerRecordEntity.setFollowingWin(producerAward.getFollowingWin());
        return producerRecordEntity;
    }

}

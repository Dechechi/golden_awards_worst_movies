package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;
import br.com.golden_awards_worst_movies.domain.model.Studio;
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
        producerEntity.setName(producer.name());
        return producerEntity;
    }

    public ProducerRecordEntity mapRecordDomainToEntity(ProducerRecord producerRecord){
        ProducerRecordEntity producerRecordEntity = new ProducerRecordEntity();
        return calculateRecord(producerRecordEntity, producerRecord);
    }

    public ProducerRecordEntity calculateRecord(ProducerRecordEntity producerRecordEntity, ProducerRecord producerRecord) {
        producerRecordEntity.setProducer(this.mapProducerDomainToEntity(producerRecord.producer()));
        int previousWin = producerRecordEntity.getPreviousWin();
        int followingWin = producerRecordEntity.getFollowingWin();
        int yearOfWin = producerRecord.yearOfWin();

        if(previousWin == 0) {
            producerRecordEntity.setFollowingWin(yearOfWin);
            producerRecordEntity.setPreviousWin(yearOfWin);
        }else if (yearOfWin > previousWin && yearOfWin < followingWin) {
            producerRecordEntity.setPreviousWin(yearOfWin);
        } else if (yearOfWin > previousWin && yearOfWin > followingWin) {
            producerRecordEntity.setFollowingWin(yearOfWin);
            producerRecordEntity.setPreviousWin(followingWin);
        }
        if (producerRecordEntity.getFollowingWin() != 0)
            producerRecordEntity.setIntervalTime(producerRecordEntity.getFollowingWin() - producerRecordEntity.getPreviousWin());
        return producerRecordEntity;
    }

}

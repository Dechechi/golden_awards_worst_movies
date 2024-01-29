package br.com.golden_awards_worst_movies.infrastructure.mapper;

import br.com.golden_awards_worst_movies.domain.model.*;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.Arrays;
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
        return new Producer(producer.getId(),producer.getName(), Arrays.stream(producer.getAwardYears().split(";"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList()));
    }

    public ProducerAward mapAwardEntityRecordToDomain(ProducerRecordEntity producerRecordEntity){
        return new ProducerAward(mapProducerEntityToDomain(producerRecordEntity.getProducer()),
                producerRecordEntity.getIntervalTime(),
                producerRecordEntity.getPreviousWin(),
                producerRecordEntity.getFollowingWin());
    }

}

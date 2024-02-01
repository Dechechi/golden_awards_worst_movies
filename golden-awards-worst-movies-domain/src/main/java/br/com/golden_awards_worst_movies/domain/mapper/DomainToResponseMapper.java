package br.com.golden_awards_worst_movies.domain.mapper;

import br.com.golden_awards_worst_movies.domain.dto.*;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.model.Studio;

import java.util.List;
import java.util.stream.Collectors;

public class DomainToResponseMapper {

    public MovieResponse mapToMovieResponse(Movie movie){
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId() != null ? movie.getId() : null);
        movieResponse.setYear(movie.getYear());
        movieResponse.setProducers(movie.getProducers().stream().map(this::mapProducerResponse).collect(Collectors.toList()));
        movieResponse.setStudios(movie.getStudios().stream().map(this::mapStudioResponse).collect(Collectors.toList()));
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setWinner(movie.isWinner());
        return movieResponse;
    }

    public ProducerResponse mapProducerResponse(Producer producer){
        ProducerResponse producerResponse = new ProducerResponse();
        producerResponse.setName(producer.getName());
        return producerResponse;
    }

    public StudioResponse mapStudioResponse(Studio studio){
        StudioResponse studioResponse = new StudioResponse();
        studioResponse.setName(studio.getName());
        return studioResponse;
    }

    public ProducersAwardsResponse mapToAwardsResponse(List<RecordResponse> maxRecordResponse, List<RecordResponse> minRecordResponse){
        ProducersAwardsResponse awards = new ProducersAwardsResponse();
        awards.setMax(maxRecordResponse);
        awards.setMin(minRecordResponse);
        return awards;
    }

    public List<RecordResponse> mapToRecordResponse(List<ProducerAward> producerAward){
        return producerAward.stream().map(award -> {
            RecordResponse recordResponse = new RecordResponse();
            recordResponse.setProducer(award.getProducer().getName());
            recordResponse.setInterval(award.getInterval());
            recordResponse.setPreviousWin(award.getPreviousWin());
            recordResponse.setFollowingWin(award.getFollowingWin());
            return recordResponse;
        }).collect(Collectors.toList());
    }

}

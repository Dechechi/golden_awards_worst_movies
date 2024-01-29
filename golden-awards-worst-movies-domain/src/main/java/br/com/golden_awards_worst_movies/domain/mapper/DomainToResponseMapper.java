package br.com.golden_awards_worst_movies.domain.mapper;

import br.com.golden_awards_worst_movies.domain.dto.MovieResponse;
import br.com.golden_awards_worst_movies.domain.dto.ProducersAwardsResponse;
import br.com.golden_awards_worst_movies.domain.dto.RecordResponse;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProducersAwardsResponse mapToAwardsResponse(List<RecordResponse> maxRecordResponse, List<RecordResponse> minRecordResponse){
        ProducersAwardsResponse awards = new ProducersAwardsResponse();
        awards.setMax(maxRecordResponse);
        awards.setMin(minRecordResponse);
        return awards;
    }

    public List<RecordResponse> mapToRecordResponse(List<ProducerAward> producerAward){
        return producerAward.stream().map(award -> {
            RecordResponse recordResponse = new RecordResponse();
            recordResponse.setProducer(award.producer().name());
            recordResponse.setInterval(award.interval());
            recordResponse.setPreviousWin(award.previousWin());
            recordResponse.setFollowingWin(award.followingWin());
            return recordResponse;
        }).collect(Collectors.toList());
    }

}

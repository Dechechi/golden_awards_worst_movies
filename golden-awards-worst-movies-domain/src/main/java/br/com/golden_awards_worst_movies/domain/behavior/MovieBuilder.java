package br.com.golden_awards_worst_movies.domain.behavior;

import br.com.golden_awards_worst_movies.domain.enums.WinnerBoolean;
import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class MovieBuilder {

    public static Movie createMovieFromRequest(MovieRequest movieRequest) throws InvalidWinnerOptionException {
        return new Movie(Integer.parseInt(movieRequest.getYear()),
                movieRequest.getTitle(),
                splitStudios(movieRequest.getStudios()),
                splitProducers(movieRequest.getProducers()),
                WinnerBoolean.valueFrom(movieRequest.getWinner()));
    }

    public static Movie createMovieFromRequestWithId(MovieRequest movieRequest, Long id) throws InvalidWinnerOptionException {
        return new Movie(id,
                Integer.parseInt(movieRequest.getYear()),
                movieRequest.getTitle(),
                splitStudios(movieRequest.getStudios()),
                splitProducers(movieRequest.getProducers()),
                WinnerBoolean.valueFrom(movieRequest.getWinner()));
    }

    public static List<Studio> splitStudios(String studios){
        return Arrays.stream(studios.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Studio::new).toList();
    }

    public static List<Producer> splitProducers(String producers){
        return Arrays.stream(producers.split("\\s*(,|\\band\\b)"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Producer::new).toList();
    }

}

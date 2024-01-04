package br.com.golden_awards_worst_movies.domain.behavior;

import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class MovieBuilder {

    public static Movie createMovieFromRequest(MovieRequest movieRequest){
        int year = Integer.parseInt(movieRequest.getYear());
        List<Studio> studios = Arrays.stream(movieRequest.getStudios().split(",")).map(Studio::new).toList();
        List<Producer> producers = Arrays.stream(movieRequest.getProducers().split(",")).map(Producer::new).toList();
        boolean winner = Boolean.parseBoolean(movieRequest.getWinner());
        return new Movie(year, movieRequest.getTitle(), studios, producers, winner);
    }

    public static Movie createMovieFromRequestWithId(MovieRequest movieRequest, Long id){
        int year = Integer.parseInt(movieRequest.getYear());
        List<Studio> studios = Arrays.stream(movieRequest.getStudios().split(",")).map(Studio::new).toList();
        List<Producer> producers = Arrays.stream(movieRequest.getProducers().split(",")).map(Producer::new).toList();
        boolean winner = Boolean.parseBoolean(movieRequest.getWinner());
        return new Movie(id, year, movieRequest.getTitle(), studios, producers, winner);
    }

}

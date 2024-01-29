package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.domain.dto.MovieResponse;
import br.com.golden_awards_worst_movies.domain.exception.BaseException;
import br.com.golden_awards_worst_movies.domain.mapper.DomainToResponseMapper;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    MovieService movieService;
    DomainToResponseMapper domainToResponseMapper;

    @Autowired
    public MovieController(MovieService movieService, DomainToResponseMapper domainToResponseMapper) {
        this.movieService = movieService;
        this.domainToResponseMapper = domainToResponseMapper;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest movieRequest) throws BaseException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(domainToResponseMapper.mapToMovieResponse(movieService.createMovie(
                       new Movie.Builder().fromRequest(movieRequest).build()))
                );
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAllMovies());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable("id") Long id) throws BaseException {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findMovie(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long id) throws BaseException{
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Movie with ID %d deleted with success", id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable("id") Long id, @RequestBody MovieRequest movieRequest) throws BaseException{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(domainToResponseMapper.mapToMovieResponse(movieService.updateMovie(
                new Movie.Builder().fromRequestWithId(id,movieRequest).build()
                )));
    }

}

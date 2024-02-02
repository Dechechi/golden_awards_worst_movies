package br.com.golden_awards_worst_movies.domain.service.impl;

import br.com.golden_awards_worst_movies.domain.service.MovieService;
import br.com.golden_awards_worst_movies.domain.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.service.StudioService;
import br.com.golden_awards_worst_movies.domain.exception.MovieAlreadyExistsException;
import br.com.golden_awards_worst_movies.domain.exception.MovieDontExistException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    final MovieRepositoryI movieRepositoryI;
    final StudioService studioService;
    final ProducerService producerService;

    @Autowired
    public MovieServiceImpl(MovieRepositoryI movieRepositoryI,
                            StudioService studioService,
                            ProducerService producerService) {
        this.movieRepositoryI = movieRepositoryI;
        this.studioService = studioService;
        this.producerService = producerService;
    }

    @Override
    public Movie createMovie(Movie movie) {
        List<Producer> producers = producerService.getExistingAndNewProducers(movie.getProducers());
        List<Studio> studios = studioService.getExistingAndNewStudios(movie.getStudios());
        Movie createMovie = Movie.Builder.builder().copy(movie).withProducers(producers).withStudios(studios).build();
        movieRepositoryI.save(createMovie);
        checkMovieRecord(createMovie);
        return createMovie;
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieDontExistException, MovieAlreadyExistsException {
        return createMovie(movie);
//        getMovieWithProducersAndStudios(movie.getId());
//
//        MovieEntity movieEntity;
//        try{
//            movieEntity = movieSpringRepository.save(domainToEntityMapper.mapNewMovie(movie));
//        } catch (DataIntegrityViolationException ex){
//            throw new MovieAlreadyExistsException
//                    ("You are trying to update a movie to a title and year that already exists");
//        }
//        Movie updateMovie = entityToDomainMapper.mapMovieEntityToDomain(movieSpringRepository.save(movieEntity));
//        checkMovieRecord(movieEntity);
//
//        return updateMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepositoryI.delete(id);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepositoryI.findAll();
    }

    @Override
    public Movie findMovie(Long id) {
        return movieRepositoryI.findById(id);
    }

    public void checkMovieRecord(Movie movie){
        if (movie.isWinner()){
            for (Producer producer : movie.getProducers()) {
                producerService.addAwardToProducer(producer, movie.getYear());
            }
        }
    }
}

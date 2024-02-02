package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.exception.MovieAlreadyExistsException;
import br.com.golden_awards_worst_movies.domain.exception.MovieDontExistException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.MovieSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    final MovieSpringRepository movieSpringRepository;
    final MovieRepositoryI movieRepositoryI;
    final StudioService studioService;
    final ProducerService producerService;
    final ProducerAwardService producerAwardService;
    final DomainToEntityMapper domainToEntityMapper;
    final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public MovieServiceImpl(MovieSpringRepository movieSpringRepository,
                            MovieRepositoryI movieRepositoryI, DomainToEntityMapper domainToEntityMapper,
                            StudioService studioService,
                            ProducerService producerService,
                            EntityToDomainMapper entityToDomainMapper,
                            ProducerAwardService producerAwardService) {
        this.movieSpringRepository = movieSpringRepository;
        this.movieRepositoryI = movieRepositoryI;
        this.domainToEntityMapper = domainToEntityMapper;
        this.studioService = studioService;
        this.producerService = producerService;
        this.entityToDomainMapper = entityToDomainMapper;
        this.producerAwardService = producerAwardService;
    }

    @Override
    public Movie createMovie(Movie movie) throws MovieAlreadyExistsException {
        MovieEntity movieEntity = domainToEntityMapper.mapNewMovie(movie);
        movieEntity.setProducers(producerService.getExistingAndNewProducersAsEntity(movie.getProducers()));
        movieEntity.setStudios(studioService.getExistingAndNewStudiosAsEntity(movie.getStudios()));
        try {
            movieSpringRepository.save(movieEntity);
        } catch (DataIntegrityViolationException ex){
            throw new MovieAlreadyExistsException("The movie you are trying to create already exists");
        }
        checkMovieRecord(movieEntity);
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieDontExistException, MovieAlreadyExistsException {
        getMovieWithProducersAndStudios(movie.getId());

        MovieEntity movieEntity;
        try{
            movieEntity = movieSpringRepository.save(domainToEntityMapper.mapNewMovie(movie));
        } catch (DataIntegrityViolationException ex){
            throw new MovieAlreadyExistsException
                    ("You are trying to update a movie to a title and year that already exists");
        }
        Movie updateMovie = entityToDomainMapper.mapMovieEntityToDomain(movieSpringRepository.save(movieEntity));
        checkMovieRecord(movieEntity);

        return updateMovie;
    }

    @Override
    public void deleteMovie(Long id) throws MovieDontExistException {
        movieSpringRepository.delete(domainToEntityMapper.mapNewMovie(findMovie(id)));
    }

    @Override
    public List<Movie> findAllMovies() {
        List<MovieEntity> movieEntities = movieSpringRepository.findAll();
        return entityToDomainMapper.mapMovieEntityListToDomainList(movieEntities);
    }

    @Override
    public Movie findMovie(Long id) throws MovieDontExistException {
        MovieEntity movieEntity = getMovieWithProducersAndStudios(id);

        return entityToDomainMapper.mapMovieEntityToDomain(movieEntity);
    }

    public MovieEntity getMovieWithProducersAndStudios(Long id) throws MovieDontExistException {
        Optional<MovieEntity> movieEntity = movieSpringRepository.findById(id);

        if (movieEntity.isEmpty()){
            throw new MovieDontExistException(String.format("Movie with id %d does not exist", id));
        }

        return movieEntity.get();
    }

    public void checkMovieRecord(MovieEntity movie){
        if (movie.isWinner()){
            for (ProducerEntity producer : movie.getProducers()) {
                producerService.addAwardToProducer(producer, movie.getReleaseYear());
            }
        }
    }

    public void checkMovieRecord2(Movie movie){
        if (movie.isWinner()){
            for (Producer producer : movie.getProducers()) {
                producerService.addAwardToProducer(producer, movie.getYear()));
            }
        }
    }
}

package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.behavior.ProducerRecordBuilder;
import br.com.golden_awards_worst_movies.domain.exception.MovieDontExistException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    StudioService studioService;
    ProducerService producerService;
    ProducerRecordService producerRecordService;
    DomainToEntityMapper domainToEntityMapper;
    EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            DomainToEntityMapper domainToEntityMapper,
                            StudioService studioService,
                            ProducerService producerService,
                            EntityToDomainMapper entityToDomainMapper,
                            ProducerRecordService producerRecordService) {
        this.movieRepository = movieRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.studioService = studioService;
        this.producerService = producerService;
        this.entityToDomainMapper = entityToDomainMapper;
        this.producerRecordService = producerRecordService;
    }

    @Override
    public Movie createMovie(Movie movie) {
        MovieEntity movieEntity = domainToEntityMapper.mapMovieDomainToEntity(movie);
        movieEntity.setProducers(producerService.getExistingAndNewProducersAsEntity(movie.producers()));
        movieEntity.setStudios(studioService.getExistingAndNewStudiosAsEntity(movie.studios()));
        movieRepository.save(movieEntity);
        checkMovieRecord(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) throws MovieDontExistException {
        checkMovieRecord(movie);
        getMovieWithProducersAndStudios(movie.id());

        return entityToDomainMapper.mapMovieEntityToDomain(movieRepository.save(domainToEntityMapper.mapMovieDomainToEntity(movie)));
    }

    @Override
    public void deleteMovie(Long id) throws MovieDontExistException {
        movieRepository.delete(domainToEntityMapper.mapMovieDomainToEntity(findMovie(id)));
    }

    @Override
    public List<Movie> findAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAll();
        return entityToDomainMapper.mapMovieEntityListToDomainList(movieEntities);
    }

    @Override
    public Movie findMovie(Long id) throws MovieDontExistException {
        MovieEntity movieEntity = getMovieWithProducersAndStudios(id);

        return entityToDomainMapper.mapMovieEntityToDomain(movieEntity);
    }

    public MovieEntity getMovieWithProducersAndStudios(Long id) throws MovieDontExistException {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);

        if (movieEntity.isEmpty()){
            throw new MovieDontExistException(String.format("Movie with id %d does not exist", id));
        }

        return movieEntity.get();
    }

    public void checkMovieRecord(Movie movie){
        if (movie.winner()){
            for (Producer producer : movie.producers()) {
                producerRecordService.saveProducerRecord(ProducerRecordBuilder.createRecord(producer, movie.year()));
            }
        }
    }
}

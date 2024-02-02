package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Movie;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.MovieSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieRepository implements MovieRepositoryI {

    final MovieSpringRepository repository;
    final EntityToDomainMapper entityToDomainMapper;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public MovieRepository(MovieSpringRepository repository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.repository = repository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public Movie save(Movie movie) {
        MovieEntity movieEntity;
        if(movie.getId() == null){
            movieEntity = domainToEntityMapper.mapNewMovie(movie);
        } else {
            //TODO incluir minha propria exception aqui
            movieEntity = repository.findById(movie.getId()).orElseThrow(RuntimeException::new);
            movieEntity = domainToEntityMapper.mapMovie(movieEntity, movie);
        }
        return entityToDomainMapper.mapMovieEntityToDomain(repository.save(movieEntity));
    }

    @Override
    public void delete(Long id) {
        //TODO incluir minha propria exception aqui
        MovieEntity movieEntity = repository.findById(id).orElseThrow(RuntimeException::new);
        repository.delete(movieEntity);
    }

    @Override
    public List<Movie> findAll() {
        List<MovieEntity> movieEntities = repository.findAll();
        return movieEntities.stream().map(entityToDomainMapper::mapMovieEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Movie findById(Long id) {
        //TODO incluir minha propria exception aqui
        MovieEntity movieEntity = repository.findById(id).orElseThrow(RuntimeException::new);
        return entityToDomainMapper.mapMovieEntityToDomain(movieEntity);
    }
}

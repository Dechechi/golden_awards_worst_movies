package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRepository;
import br.com.golden_awards_worst_movies.infrastructure.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    ProducerRepository producerRepository;

    private Map<ProducerEntity, List<Long>> producersMoviesMap = new HashMap<>();

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Set<ProducerEntity> getExistingAndNewProducersAsEntity(List<Producer> producers) {
        List<String> receivedProducers = producers.stream().map(Producer::name).toList();
        List<ProducerEntity> existingProducers = producerRepository.findByNameIn(producers.stream().map(Producer::name).collect(Collectors.toList()));
        List<String> existingProducersList = existingProducers.stream().map(ProducerEntity::getName).toList();
        List<String> newProducersNames = receivedProducers.stream().filter(name -> !existingProducersList.contains(name)).toList();
        List<ProducerEntity> newProducers = newProducersNames.stream().map(ProducerEntity::new).toList();
        List<ProducerEntity> allProducers = new ArrayList<>(existingProducers);
        allProducers.addAll(newProducers);
        return Set.copyOf(allProducers);
    }

    @Override
    public List<ProducerEntity> getAllProducersFromMovieId(Long id) {
        return null;
    }

    @Override
    public Map<ProducerEntity, List<Long>> getAllProducersWithMovieIds(){
//        List<Object[]> results = producerRepository.findAllProducersWithMovieIds();
//        for (Object[] result: results) {
//            ProducerEntity producerEntity = new ProducerEntity(result[0].toString());
//            Long movieId = (Long) result[1];
//
//            producersMoviesMap.computeIfAbsent(producerEntity, k -> new ArrayList<>()).add(movieId);
//        }
//        return producersMoviesMap;
        return null;
    }

    @Override
    public List<ProducerEntity> getAllProducersOfMovie(MovieEntity movie){
        return this.getAllProducersWithMovieIds().entrySet().stream()
                .filter(producer -> producer.getValue().contains(movie.getId()))
                .map(Map.Entry::getKey).toList();
    }
}

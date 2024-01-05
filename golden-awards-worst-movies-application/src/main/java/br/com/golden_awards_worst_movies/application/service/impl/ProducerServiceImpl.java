package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    ProducerRepository producerRepository;

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
}

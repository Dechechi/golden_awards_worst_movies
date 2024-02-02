package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;

import java.util.List;
import java.util.Set;

public interface ProducerService {

    List<Producer> getExistingAndNewProducers(List<Producer> producers);
    void addAwardToProducer(Producer producer, int year);

}

package br.com.golden_awards_worst_movies.domain.service;

import br.com.golden_awards_worst_movies.domain.model.Producer;

import java.util.List;

public interface ProducerService {

    List<Producer> getExistingAndNewProducers(List<Producer> producers);
    void addAwardToProducer(Producer producer, int year);

}

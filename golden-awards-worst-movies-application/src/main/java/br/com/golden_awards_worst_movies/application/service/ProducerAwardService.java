package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;

import java.util.List;

public interface ProducerAwardService {

    ProducerAward saveProducerRecord(ProducerAward producerAward);
    List<ProducerAward> findMaxProducerAwards();
    List<ProducerAward> findMinProducerAwards();
    void deleteAllByProducer(Producer producer);

}

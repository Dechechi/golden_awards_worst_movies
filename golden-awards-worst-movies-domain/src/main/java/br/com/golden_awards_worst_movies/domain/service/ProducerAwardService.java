package br.com.golden_awards_worst_movies.domain.service;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;

import java.util.List;

public interface ProducerAwardService {

    ProducerAward saveProducerRecord(ProducerAward producerAward);
    List<ProducerAward> findMaxProducerAwards();
    List<ProducerAward> findMinProducerAwards();
    void deleteAllByProducer(Producer producer);

}

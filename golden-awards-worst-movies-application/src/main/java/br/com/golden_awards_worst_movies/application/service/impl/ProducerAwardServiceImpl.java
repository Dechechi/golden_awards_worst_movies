package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerAwardSpringRepository;

import java.util.List;

public class ProducerAwardServiceImpl implements ProducerAwardService {

    final ProducerAwardSpringRepository producerAwardSpringRepository;
    final ProducerAwardRepositoryI producerAwardRepositoryI;

    public ProducerAwardServiceImpl(ProducerAwardSpringRepository producerAwardSpringRepository, ProducerAwardRepositoryI producerAwardRepositoryI) {
        this.producerAwardSpringRepository = producerAwardSpringRepository;
        this.producerAwardRepositoryI = producerAwardRepositoryI;
    }

    @Override
    public ProducerAward saveProducerRecord(ProducerAward producerAward) {
        return producerAwardRepositoryI.save(producerAward);
    }

    @Override
    public List<ProducerAward> findMaxProducerAwards() {
        return producerAwardRepositoryI.findAllWithMaxInterval();
    }

    @Override
    public List<ProducerAward> findMinProducerAwards() {
        return producerAwardRepositoryI.findAllWithMinInterval();
    }

    @Override
    public void deleteAllByProducer(Producer producer){
        producerAwardRepositoryI.deleteAllByProducer(producer);
    }
}

package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRecordRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRecordSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerRecordRepository implements ProducerRecordRepositoryI {

    final ProducerRecordSpringRepository repository;

    @Autowired
    public ProducerRecordRepository(ProducerRecordSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProducerAward findByFollowingWinAndPreviousWin(int followingWin, int previousWin) {
        return null;
    }

    @Override
    public ProducerAward save(ProducerAward producerAward) {
        return null;
    }

    @Override
    public List<ProducerAward> findAllWithMaxInterval() {
        return null;
    }

    @Override
    public List<ProducerAward> findAllWithMinInterval() {
        return null;
    }

    @Override
    public void deleteAllByProducer(Producer producer) {

    }
}

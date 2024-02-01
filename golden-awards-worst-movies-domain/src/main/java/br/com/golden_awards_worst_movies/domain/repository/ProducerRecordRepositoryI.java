package br.com.golden_awards_worst_movies.domain.repository;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;

import java.util.List;

public interface ProducerRecordRepositoryI {

    ProducerAward findByFollowingWinAndPreviousWin(int followingWin, int previousWin);
    ProducerAward save(ProducerAward producerAward);
    List<ProducerAward> findAllWithMaxInterval();
    List<ProducerAward> findAllWithMinInterval();
    void deleteAllByProducer(Producer producer);

}

package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;

import java.util.List;

public interface ProducerRecordService {

    ProducerRecord saveProducerRecord(ProducerRecord producerRecord);
    ProducerRecord updateProducerRecord(ProducerRecordEntity recordEntity, ProducerRecord producerRecord);
    ProducerRecord deleteProducerRecord(ProducerRecord producerRecord);
    ProducerRecord findProducerRecordById(ProducerRecord producerRecord);
    List<ProducerAward> findMaxProducerAwards();
    List<ProducerAward> findMinProducerAwards();

}

package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;

public interface ProducerRecordService {

    ProducerRecord createProducerRecord(ProducerRecord producerRecord);
    ProducerRecord updateProducerRecord(ProducerRecord producerRecord);
    ProducerRecord deleteProducerRecord(ProducerRecord producerRecord);
    ProducerRecord findProducerRecordById(ProducerRecord producerRecord);

}

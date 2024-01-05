package br.com.golden_awards_worst_movies.domain.behavior;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;

public class ProducerRecordBuilder {

    public static ProducerRecord createRecord(Producer producer, int year){
        return new ProducerRecord(producer, year);
    }

}

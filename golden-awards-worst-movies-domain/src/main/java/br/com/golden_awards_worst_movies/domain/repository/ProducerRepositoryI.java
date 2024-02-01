package br.com.golden_awards_worst_movies.domain.repository;

import br.com.golden_awards_worst_movies.domain.model.Producer;

import java.util.List;

public interface ProducerRepositoryI {

    List<Producer> findByNameIn(List<String> names);
    Producer save(Producer producer);
    Producer findById(Long id);

}

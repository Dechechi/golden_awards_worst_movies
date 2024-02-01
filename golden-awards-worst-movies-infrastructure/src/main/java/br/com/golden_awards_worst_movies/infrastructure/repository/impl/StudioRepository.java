package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudioRepository implements StudioRepositoryI {

    final StudioSpringRepository repository;

    @Autowired
    public StudioRepository(StudioSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Studio> findByNameIn(List<String> names) {
        return null;
    }

    @Override
    public Studio save(Studio studio) {
        return null;
    }
}

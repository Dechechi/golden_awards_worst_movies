package br.com.golden_awards_worst_movies.domain.repository;

import br.com.golden_awards_worst_movies.domain.model.Studio;

import java.util.List;

public interface StudioRepositoryI {

    List<Studio> findByNameIn(List<String> names);
    Studio save(Studio studio);

}

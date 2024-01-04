package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudioService {

    Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios);
    List<StudioEntity> getAllStudiosFromMovieId(Long id);
    Map<StudioEntity, List<Long>> getAllStudiosWithMovieIds();
    List<StudioEntity> getAllStudiosOfMovie(MovieEntity movie);

}

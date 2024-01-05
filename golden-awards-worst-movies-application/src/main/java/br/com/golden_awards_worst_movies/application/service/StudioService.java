package br.com.golden_awards_worst_movies.application.service;

import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;

import java.util.List;
import java.util.Set;

public interface StudioService {

    Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios);

}

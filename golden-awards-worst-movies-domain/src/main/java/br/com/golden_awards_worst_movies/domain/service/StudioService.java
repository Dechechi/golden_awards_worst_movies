package br.com.golden_awards_worst_movies.domain.service;

import br.com.golden_awards_worst_movies.domain.model.Studio;

import java.util.List;

public interface StudioService {

    List<Studio> getExistingAndNewStudios(List<Studio> studios);

}

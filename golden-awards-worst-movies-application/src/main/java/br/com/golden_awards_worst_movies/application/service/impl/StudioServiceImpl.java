package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.MovieEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService {

    StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios) {
        List<String> receivedStudios = studios.stream().map(Studio::name).toList();
        List<StudioEntity> existingStudios = studioRepository.findByNameIn(studios.stream().map(Studio::name).collect(Collectors.toList()));
        List<String> existingStudiosList = existingStudios.stream().map(StudioEntity::getName).toList();
        List<String> newStudiosNames = receivedStudios.stream().filter(name -> !existingStudiosList.contains(name)).toList();
        List<StudioEntity> newStudios = newStudiosNames.stream().map(StudioEntity::new).toList();
        List<StudioEntity> allStudios = new ArrayList<>(existingStudios);
        allStudios.addAll(newStudios);
        return Set.copyOf(allStudios);
    }

    @Override
    public List<StudioEntity> getAllStudiosFromMovieId(Long id) {
        return null;
    }

    @Override
    public Map<StudioEntity, List<Long>> getAllStudiosWithMovieIds() {
        return null;
    }

    @Override
    public List<StudioEntity> getAllStudiosOfMovie(MovieEntity movie){
        return this.getAllStudiosWithMovieIds().entrySet().stream()
                .filter(producer -> producer.getValue().contains(movie.getId()))
                .map(Map.Entry::getKey).toList();
    }
}

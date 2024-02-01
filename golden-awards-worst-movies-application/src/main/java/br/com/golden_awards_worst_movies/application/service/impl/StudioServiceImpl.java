package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService {

    final StudioSpringRepository studioSpringRepository;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public StudioServiceImpl(StudioSpringRepository studioSpringRepository, DomainToEntityMapper domainToEntityMapper) {
        this.studioSpringRepository = studioSpringRepository;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios) {
        List<StudioEntity> existingStudios = studioSpringRepository.findByNameIn(studios.stream().map(Studio::getName).collect(Collectors.toList()));
        List<Studio> newStudiosNames = studios.stream()
                .filter(studio -> existingStudios.stream().noneMatch(
                        studioEntity -> studioEntity.getName().equalsIgnoreCase(studio.getName())
                )).toList();
        List<StudioEntity> newStudios = newStudiosNames.stream().map(
                studio -> studioSpringRepository.save(domainToEntityMapper.mapStudioDomainToEntity(studio))).toList();
        List<StudioEntity> allStudios = new ArrayList<>(existingStudios);
        allStudios.addAll(newStudios);
        return Set.copyOf(allStudios);
    }
}

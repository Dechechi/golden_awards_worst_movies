package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService {

    final StudioRepository studioRepository;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository, DomainToEntityMapper domainToEntityMapper) {
        this.studioRepository = studioRepository;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios) {
        List<StudioEntity> existingStudios = studioRepository.findByNameIn(studios.stream().map(Studio::getName).collect(Collectors.toList()));
        List<Studio> newStudiosNames = studios.stream()
                .filter(studio -> existingStudios.stream().noneMatch(
                        studioEntity -> studioEntity.getName().equalsIgnoreCase(studio.getName())
                )).toList();
        List<StudioEntity> newStudios = newStudiosNames.stream().map(
                studio -> studioRepository.save(domainToEntityMapper.mapStudioDomainToEntity(studio))).toList();
        List<StudioEntity> allStudios = new ArrayList<>(existingStudios);
        allStudios.addAll(newStudios);
        return Set.copyOf(allStudios);
    }
}

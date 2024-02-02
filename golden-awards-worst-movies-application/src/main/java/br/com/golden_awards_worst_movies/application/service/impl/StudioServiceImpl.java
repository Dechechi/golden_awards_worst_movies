package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class StudioServiceImpl implements StudioService {

    final StudioSpringRepository studioSpringRepository;
    final StudioRepositoryI studioRepositoryI;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public StudioServiceImpl(StudioSpringRepository studioSpringRepository, StudioRepositoryI studioRepositoryI, DomainToEntityMapper domainToEntityMapper) {
        this.studioSpringRepository = studioSpringRepository;
        this.studioRepositoryI = studioRepositoryI;
        this.domainToEntityMapper = domainToEntityMapper;
    }

//    @Override
//    public Set<StudioEntity> getExistingAndNewStudiosAsEntity(List<Studio> studios) {
//        List<StudioEntity> existingStudios = studioSpringRepository.findByNameIn(studios.stream().map(Studio::getName).collect(Collectors.toList()));
//        List<Studio> newStudiosNames = studios.stream()
//                .filter(studio -> existingStudios.stream().noneMatch(
//                        studioEntity -> studioEntity.getName().equalsIgnoreCase(studio.getName())
//                )).toList();
//        List<StudioEntity> newStudios = newStudiosNames.stream().map(
//                studio -> studioSpringRepository.save(domainToEntityMapper.mapNewStudio(studio))).toList();
//        List<StudioEntity> allStudios = new ArrayList<>(existingStudios);
//        allStudios.addAll(newStudios);
//        Set<Studio> test = getExistingAndNewStudiosAsEntity2(studios);
//        return Set.copyOf(allStudios);
//    }

    @Override
    public Set<Studio> getExistingAndNewStudiosAsEntity(List<Studio> studios) {
        List<Studio> existingStudios = studioRepositoryI.findByNameIn(studios.stream().map(Studio::getName).collect(Collectors.toList()));
        List<Studio> newStudiosNames = studios.stream()
                .filter(studio -> existingStudios.stream().noneMatch(
                        s -> s.getName().equalsIgnoreCase(studio.getName())
                )).toList();
        List<Studio> newStudios = newStudiosNames.stream().map(
                studioRepositoryI::save).toList();
        List<Studio> allStudios = new ArrayList<>(existingStudios);
        allStudios.addAll(newStudios);
        return Set.copyOf(allStudios);
    }
}

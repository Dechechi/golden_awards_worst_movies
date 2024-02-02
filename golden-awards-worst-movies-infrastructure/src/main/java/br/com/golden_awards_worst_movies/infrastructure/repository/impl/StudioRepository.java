package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Studio;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.StudioEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudioRepository implements StudioRepositoryI {

    final StudioSpringRepository repository;
    final EntityToDomainMapper entityToDomainMapper;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public StudioRepository(StudioSpringRepository repository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.repository = repository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public List<Studio> findByNameIn(List<String> names) {
        List<StudioEntity> studios = repository.findByNameIn(names);
        return studios.stream().map(entityToDomainMapper::mapStudioEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Studio save(Studio studio) {
        StudioEntity studioEntity;
        if(studio.getId() == null){
            studioEntity = domainToEntityMapper.mapNewStudio(studio);
        } else {
            //TODO incluir minha propria exception aqui
            studioEntity = repository.findById(studio.getId()).orElseThrow(RuntimeException::new);
            studioEntity = domainToEntityMapper.mapStudio(studioEntity, studio);
        }
        return entityToDomainMapper.mapStudioEntityToDomain(repository.save(studioEntity));
    }
}

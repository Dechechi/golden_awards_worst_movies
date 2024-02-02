package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProducerRepository implements ProducerRepositoryI {

    final ProducerSpringRepository repository;
    final EntityToDomainMapper entityToDomainMapper;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public ProducerRepository(ProducerSpringRepository repository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.repository = repository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public List<Producer> findByNameIn(List<String> names) {
        List<ProducerEntity> producers = repository.findByNameIn(names);
        return producers.stream().map(entityToDomainMapper::mapProducerEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Producer save(Producer producer) {
        ProducerEntity producerEntity;
        if(producer.getId() == null){
            producerEntity = domainToEntityMapper.mapNewProducer(producer);
        } else {
            //TODO incluir minha propria exception aqui
            producerEntity = repository.findById(producer.getId()).orElseThrow(RuntimeException::new);
            producerEntity = domainToEntityMapper.mapProducer(producerEntity, producer);
        }
        return entityToDomainMapper.mapProducerEntityToDomain(repository.save(producerEntity));
    }

    @Override
    public Producer findById(Long id) {
        Optional<ProducerEntity> producerEntity = repository.findById(id);
        if(producerEntity.isPresent()){
            return entityToDomainMapper.mapProducerEntityToDomain(producerEntity.get());
        }
        //TODO incluir minha propria exception aqui
        throw new RuntimeException();
    }
}

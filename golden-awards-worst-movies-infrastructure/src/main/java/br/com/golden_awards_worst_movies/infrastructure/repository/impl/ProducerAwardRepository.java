package br.com.golden_awards_worst_movies.infrastructure.repository.impl;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerAwardEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerAwardSpringRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProducerAwardRepository implements ProducerAwardRepositoryI {

    final ProducerAwardSpringRepository repository;
    final EntityToDomainMapper entityToDomainMapper;
    final DomainToEntityMapper domainToEntityMapper;

    @Autowired
    public ProducerAwardRepository(ProducerAwardSpringRepository repository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper) {
        this.repository = repository;
        this.entityToDomainMapper = entityToDomainMapper;
        this.domainToEntityMapper = domainToEntityMapper;
    }

    @Override
    public ProducerAward findByFollowingWinAndPreviousWin(int followingWin, int previousWin) {
        Optional<ProducerAwardEntity> producerAwardEntity = repository.findByFollowingWinAndPreviousWin(followingWin, previousWin);
        if (producerAwardEntity.isPresent()){
            return entityToDomainMapper.mapAwardEntityRecordToDomain(producerAwardEntity.get());
        }
        //TODO imcluir minha propria exception aqui
        throw new RuntimeException();
    }

    @Override
    public ProducerAward save(ProducerAward producerAward) {
        ProducerAwardEntity producerAwardEntity;
        if(producerAward.getId() == null){
            producerAwardEntity = domainToEntityMapper.mapNewAward(producerAward);
        } else {
            producerAwardEntity = repository.findById(producerAward.getId()).orElseThrow(RuntimeException::new);
            producerAwardEntity = domainToEntityMapper.mapAward(producerAwardEntity, producerAward);
        }
        return entityToDomainMapper.mapAwardEntityRecordToDomain(repository.save(producerAwardEntity));
    }

    @Override
    public List<ProducerAward> findAllWithMaxInterval() {
        List<ProducerAwardEntity> awardEntities = repository.findAllWithMaxInterval();
        return awardEntities.stream().map(entityToDomainMapper::mapAwardEntityRecordToDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProducerAward> findAllWithMinInterval() {
        List<ProducerAwardEntity> awardEntities = repository.findAllWithMinInterval();
        return awardEntities.stream().map(entityToDomainMapper::mapAwardEntityRecordToDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllByProducer(Producer producer) {
        repository.deleteAllByProducerId(producer.getId());
    }
}

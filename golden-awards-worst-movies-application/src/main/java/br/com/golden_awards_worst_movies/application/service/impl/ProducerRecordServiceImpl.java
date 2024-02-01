package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRecordSpringRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProducerRecordServiceImpl implements ProducerRecordService {

    final ProducerRecordSpringRepository producerRecordSpringRepository;
    final DomainToEntityMapper domainToEntityMapper;
    final EntityToDomainMapper entityToDomainMapper;

    public ProducerRecordServiceImpl(ProducerRecordSpringRepository producerRecordSpringRepository, DomainToEntityMapper domainToEntityMapper,
                                     EntityToDomainMapper entityToDomainMapper) {
        this.producerRecordSpringRepository = producerRecordSpringRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public ProducerAward saveProducerRecord(ProducerAward producerAward) {
        Optional<ProducerRecordEntity> recordEntity = producerRecordSpringRepository.findByFollowingWinAndPreviousWin(
                producerAward.getFollowingWin(), producerAward.getPreviousWin());

        if(recordEntity.isPresent()){
            return entityToDomainMapper.mapAwardEntityRecordToDomain(recordEntity.get());
        }

        return entityToDomainMapper.mapAwardEntityRecordToDomain(
                producerRecordSpringRepository.save(domainToEntityMapper.mapAwardDomainToEntity(producerAward))
        );
    }

    @Override
    public List<ProducerAward> findMaxProducerAwards() {
        List<ProducerRecordEntity> maxRecordEntity = producerRecordSpringRepository.findAllWithMaxInterval();
        return maxRecordEntity.stream().map(
                entityToDomainMapper::mapAwardEntityRecordToDomain).collect(Collectors.toList());
    }

    @Override
    public List<ProducerAward> findMinProducerAwards() {
        List<ProducerRecordEntity> maxRecordEntity = producerRecordSpringRepository.findAllWithMinInterval();
        return maxRecordEntity.stream().map(
                entityToDomainMapper::mapAwardEntityRecordToDomain).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void deleteAllByProducer(ProducerEntity producerEntity){
        producerRecordSpringRepository.deleteAllByProducer(producerEntity);
    }
}

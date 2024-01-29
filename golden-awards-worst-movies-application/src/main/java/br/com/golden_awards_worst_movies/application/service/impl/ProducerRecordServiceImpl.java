package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProducerRecordServiceImpl implements ProducerRecordService {

    private ProducerRecordRepository producerRecordRepository;
    private DomainToEntityMapper domainToEntityMapper;
    private EntityToDomainMapper entityToDomainMapper;

    public ProducerRecordServiceImpl(ProducerRecordRepository producerRecordRepository,
                                     DomainToEntityMapper domainToEntityMapper,
                                     EntityToDomainMapper entityToDomainMapper) {
        this.producerRecordRepository = producerRecordRepository;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public ProducerAward saveProducerRecord(ProducerAward producerAward) {
        Optional<ProducerRecordEntity> recordEntity = producerRecordRepository.findByFollowingWinAndPreviousWin(producerAward.followingWin(), producerAward.previousWin());

        if(recordEntity.isPresent()){
            return entityToDomainMapper.mapAwardEntityRecordToDomain(recordEntity.get());
        }

        return entityToDomainMapper.mapAwardEntityRecordToDomain(
                producerRecordRepository.save(domainToEntityMapper.mapAwardDomainToEntity(producerAward))
        );
    }

//    @Override
//    public ProducerRecord updateProducerRecord(ProducerRecordEntity recordEntity, ProducerRecord producerRecord) {
//        return entityToDomainMapper.mapRecordEntityToDomain(
//                producerRecordRepository.save(domainToEntityMapper.calculateRecord(recordEntity, producerRecord))
//        );
//    }

    @Override
    public List<ProducerAward> findMaxProducerAwards() {
        List<ProducerRecordEntity> maxRecordEntity = producerRecordRepository.findAllWithMaxInterval();
        return maxRecordEntity.stream().map(record -> entityToDomainMapper.mapAwardEntityRecordToDomain(record)).collect(Collectors.toList());
    }

    @Override
    public List<ProducerAward> findMinProducerAwards() {
        List<ProducerRecordEntity> maxRecordEntity = producerRecordRepository.findAllWithMinInterval();
        return maxRecordEntity.stream().map(record -> entityToDomainMapper.mapAwardEntityRecordToDomain(record)).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void deleteAllByProducer(ProducerEntity producerEntity){
        producerRecordRepository.deleteAllByProducer(producerEntity);
    }
}

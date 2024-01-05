package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerRecordEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ProducerRecord saveProducerRecord(ProducerRecord producerRecord) {
        Optional<ProducerRecordEntity> recordEntity = producerRecordRepository.findByProducer(
                domainToEntityMapper.mapProducerDomainToEntity(producerRecord.producer())
        );

        if(recordEntity.isPresent()){
            return this.updateProducerRecord(recordEntity.get() ,producerRecord);
        }

        return entityToDomainMapper.mapRecordEntityToDomain(
                producerRecordRepository.save(domainToEntityMapper.mapRecordDomainToEntity(producerRecord))
        );
    }

    @Override
    public ProducerRecord updateProducerRecord(ProducerRecordEntity recordEntity, ProducerRecord producerRecord) {
        return entityToDomainMapper.mapRecordEntityToDomain(
                producerRecordRepository.save(domainToEntityMapper.calculateRecord(recordEntity, producerRecord))
        );
    }

    @Override
    public ProducerRecord deleteProducerRecord(ProducerRecord producerRecord) {
        return null;
    }

    @Override
    public ProducerRecord findProducerRecordById(ProducerRecord producerRecord) {
        return null;
    }
}

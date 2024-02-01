package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    final ProducerSpringRepository producerSpringRepository;
    final ProducerRecordService producerRecordService;
    final DomainToEntityMapper domainToEntityMapper;
    final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public ProducerServiceImpl(ProducerSpringRepository producerSpringRepository, ProducerRecordService producerRecordService, DomainToEntityMapper domainToEntityMapper, EntityToDomainMapper entityToDomainMapper) {
        this.producerSpringRepository = producerSpringRepository;
        this.producerRecordService = producerRecordService;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Set<ProducerEntity> getExistingAndNewProducersAsEntity(List<Producer> producers) {
        List<ProducerEntity> existingProducers = producerSpringRepository.findByNameIn(producers.stream().map(Producer::getName).collect(Collectors.toList()));
        List<Producer> newProducersNames = producers.stream()
                .filter(producer -> existingProducers.stream().noneMatch(
                        producerEntity -> producerEntity.getName().equalsIgnoreCase(producer.getName()))).toList();
        List<ProducerEntity> newProducers = newProducersNames.stream().map(
                producer -> producerSpringRepository.save(domainToEntityMapper.mapProducerDomainToEntity(producer))).toList();
        List<ProducerEntity> allProducers = new ArrayList<>(existingProducers);
        allProducers.addAll(newProducers);
        return Set.copyOf(allProducers);
    }

    public void addAwardToProducer(ProducerEntity producer, int year){
        ProducerEntity producerEntity = producerSpringRepository.findById(producer.getId()).orElse(null);
        if (producerEntity!= null && !producerEntity.getAwardYears().contains(String.valueOf(year))){
            if(producerEntity.getAwardYears().length()>0){
                producerEntity.setAwardYears(producerEntity.getAwardYears().concat(";"+year));
            } else {
                producerEntity.setAwardYears(producerEntity.getAwardYears().concat(String.valueOf(year)));
            }
            producerSpringRepository.save(producerEntity);
            updateRecordInterval(entityToDomainMapper.mapProducerEntityToDomain(producerEntity));
        }
    }

    public void updateRecordInterval(Producer producer){
        if (producer.getAwardYears().size() > 1){
            producerRecordService.deleteAllByProducer(domainToEntityMapper.mapProducerDomainToEntity(producer));
            producer.getAwardYears().sort(Comparator.naturalOrder());
            for (int i = 0; i < producer.getAwardYears().size() - 1;i++){
                int currentYear = producer.getAwardYears().get(i);
                int nextYear = producer.getAwardYears().get(i+1);
                int interval = Math.abs(currentYear - nextYear);
                producerRecordService.saveProducerRecord(ProducerAward.Builder.builder().withProducer(producer)
                        .withInterval(interval)
                        .withPreviousWin(currentYear)
                        .withFollowingWin(nextYear).build());
            }
        }
    }
}

package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.behavior.ProducerAwardBuilder;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.infrastructure.entity.ProducerEntity;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    final ProducerRepository producerRepository;
    final ProducerRecordService producerRecordService;
    final DomainToEntityMapper domainToEntityMapper;
    final EntityToDomainMapper entityToDomainMapper;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository, ProducerRecordService producerRecordService, DomainToEntityMapper domainToEntityMapper, EntityToDomainMapper entityToDomainMapper) {
        this.producerRepository = producerRepository;
        this.producerRecordService = producerRecordService;
        this.domainToEntityMapper = domainToEntityMapper;
        this.entityToDomainMapper = entityToDomainMapper;
    }

    @Override
    public Set<ProducerEntity> getExistingAndNewProducersAsEntity(List<Producer> producers) {
        //List<String> receivedProducers = producers.stream().map(Producer::name).toList();
        List<ProducerEntity> existingProducers = producerRepository.findByNameIn(producers.stream().map(Producer::name).collect(Collectors.toList()));
        //List<String> existingProducersList = existingProducers.stream().map(ProducerEntity::getName).toList();
        List<Producer> newProducersNames = producers.stream()
                .filter(producer -> existingProducers.stream().noneMatch(
                        producerEntity -> producerEntity.getName().equalsIgnoreCase(producer.name()))).toList();
        List<ProducerEntity> newProducers = newProducersNames.stream().map(
                producer -> producerRepository.save(domainToEntityMapper.mapProducerDomainToEntity(producer))).toList();
        List<ProducerEntity> allProducers = new ArrayList<>(existingProducers);
        allProducers.addAll(newProducers);
        return Set.copyOf(allProducers);
    }

    public void addAwardToProducer(ProducerEntity producer, int year){
        ProducerEntity producerEntity = producerRepository.findById(producer.getId()).orElse(null);
        if (producerEntity!= null && !producerEntity.getAwardYears().contains(String.valueOf(year))){
            if(producerEntity.getAwardYears().length()>0){
                producerEntity.setAwardYears(producerEntity.getAwardYears().concat(";"+year));
            } else {
                producerEntity.setAwardYears(producerEntity.getAwardYears().concat(String.valueOf(year)));
            }
            producerRepository.save(producerEntity);
            updateRecordInterval(entityToDomainMapper.mapProducerEntityToDomain(producerEntity));
        }
    }

    public void updateRecordInterval(Producer producer){
        if (producer.awardYears().size() > 1){
            producerRecordService.deleteAllByProducer(domainToEntityMapper.mapProducerDomainToEntity(producer));
            producer.awardYears().sort(Comparator.naturalOrder());
            for (int i = 0; i < producer.awardYears().size() - 1;i++){
                int currentYear = producer.awardYears().get(i);
                int nextYear = producer.awardYears().get(i+1);
                int interval = Math.abs(currentYear - nextYear);
                producerRecordService.saveProducerRecord(ProducerAwardBuilder.createAward(producer, interval, currentYear, nextYear));
            }
        }
    }
}

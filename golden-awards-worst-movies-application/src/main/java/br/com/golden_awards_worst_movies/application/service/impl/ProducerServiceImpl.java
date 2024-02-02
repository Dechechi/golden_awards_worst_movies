package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class ProducerServiceImpl implements ProducerService {

    final ProducerRepositoryI producerRepositoryI;
    final ProducerAwardService producerAwardService;

    @Autowired
    public ProducerServiceImpl(ProducerRepositoryI producerRepositoryI, ProducerAwardService producerAwardService) {
        this.producerRepositoryI = producerRepositoryI;
        this.producerAwardService = producerAwardService;
    }

    @Override
    public List<Producer> getExistingAndNewProducers(List<Producer> producers){
        List<Producer> existingProducers = producerRepositoryI.findByNameIn(producers.stream().map(Producer::getName).collect(Collectors.toList()));
        List<Producer> newProducersNames = producers.stream()
                .filter(producer -> existingProducers.stream().noneMatch(
                        p -> p.getName().equalsIgnoreCase(producer.getName())))
                .toList();
//        List<Producer> newProducers = newProducersNames.stream().map(
//                producerRepositoryI::save).toList();
        List<Producer> allProducers = new ArrayList<>(existingProducers);
        allProducers.addAll(newProducersNames);
        return allProducers;
    }

    @Override
    public void addAwardToProducer(Producer producer, int year){
        if(!producer.getAwardYears().contains(year)){
            List<Integer> addedYears = producer.getAwardYears();
            addedYears.add(year);
            Producer updateProducer = Producer.Builder.builder().copy(producer).withAwardYears(addedYears).build();
            producerRepositoryI.save(updateProducer);
            updateRecordInterval(producer);
        }
    }

    public void updateRecordInterval(Producer producer){
        if (producer.getAwardYears().size() > 1){
            producerAwardService.deleteAllByProducer(producer);
            producer.getAwardYears().sort(Comparator.naturalOrder());
            for (int i = 0; i < producer.getAwardYears().size() - 1;i++){
                int currentYear = producer.getAwardYears().get(i);
                int nextYear = producer.getAwardYears().get(i+1);
                int interval = Math.abs(currentYear - nextYear);
                producerAwardService.saveProducerRecord(ProducerAward.Builder.builder().withProducer(producer)
                        .withInterval(interval)
                        .withPreviousWin(currentYear)
                        .withFollowingWin(nextYear).build());
            }
        }
    }
}

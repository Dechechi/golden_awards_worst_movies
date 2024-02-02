package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.domain.dto.ProducersAwardsResponse;
import br.com.golden_awards_worst_movies.domain.mapper.DomainToResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/awards")
public class AwardsController {

    ProducerAwardService producerAwardService;
    DomainToResponseMapper domainToResponseMapper;

    @Autowired
    public AwardsController(ProducerAwardService producerAwardService, DomainToResponseMapper domainToResponseMapper) {
        this.producerAwardService = producerAwardService;
        this.domainToResponseMapper = domainToResponseMapper;
    }

    @GetMapping
    public ResponseEntity<ProducersAwardsResponse> getAwards(){
        ProducersAwardsResponse producersAwardsResponse = domainToResponseMapper.mapToAwardsResponse(
                domainToResponseMapper.mapToRecordResponse(producerAwardService.findMaxProducerAwards()),
                domainToResponseMapper.mapToRecordResponse(producerAwardService.findMinProducerAwards()));
        return ResponseEntity.status(HttpStatus.OK).body(producersAwardsResponse);
    }
}

package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.ProducerRecordService;
import br.com.golden_awards_worst_movies.domain.dto.ProducersAwardsResponse;
import br.com.golden_awards_worst_movies.domain.mapper.DomainToResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/awards")
public class AwardsController {

    ProducerRecordService producerRecordService;
    DomainToResponseMapper domainToResponseMapper;

    @Autowired
    public AwardsController(ProducerRecordService producerRecordService, DomainToResponseMapper domainToResponseMapper) {
        this.producerRecordService = producerRecordService;
        this.domainToResponseMapper = domainToResponseMapper;
    }

    @GetMapping
    public ResponseEntity<ProducersAwardsResponse> getAwards(){
        ProducersAwardsResponse producersAwardsResponse = domainToResponseMapper.mapToAwardsResponse(
                domainToResponseMapper.mapToRecordResponse(producerRecordService.findMaxProducerAwards()),
                domainToResponseMapper.mapToRecordResponse(producerRecordService.findMinProducerAwards()));
        return ResponseEntity.status(HttpStatus.OK).body(producersAwardsResponse);
    }
}

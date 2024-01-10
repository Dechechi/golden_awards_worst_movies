package br.com.golden_awards_worst_movies.application.controller;

import br.com.golden_awards_worst_movies.application.service.PipelineDataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/csvReader")
public class CsvController {

    PipelineDataReader pipelineDataReader;

    @Autowired
    public CsvController(PipelineDataReader pipelineDataReader) {
        this.pipelineDataReader = pipelineDataReader;
    }

    @GetMapping
    public ResponseEntity<String> socorro(){
        pipelineDataReader.readMoviesFromCsv();
        return ResponseEntity.status(HttpStatus.OK).body("FOOOOOI?");
    }
}

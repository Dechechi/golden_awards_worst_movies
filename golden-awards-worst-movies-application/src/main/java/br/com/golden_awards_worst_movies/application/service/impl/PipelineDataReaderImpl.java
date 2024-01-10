package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.config.CsvReaderConfig;
import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.application.service.PipelineDataReader;
import br.com.golden_awards_worst_movies.domain.behavior.MovieBuilder;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;
import br.com.golden_awards_worst_movies.domain.exception.MovieAlreadyExistsException;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class PipelineDataReaderImpl implements PipelineDataReader {

    private static Logger LOG = LoggerFactory.getLogger(PipelineDataReaderImpl.class);

    MovieService movieService;
    CsvReaderConfig csvReaderConfig;

    @Autowired
    public PipelineDataReaderImpl(MovieService movieService, CsvReaderConfig csvReaderConfig) {
        this.movieService = movieService;
        this.csvReaderConfig = csvReaderConfig;
    }

    @Override
    public void readMoviesFromCsv() {
        try {
            URL resourceURL = Objects.requireNonNull(getClass().getClassLoader().getResource(csvReaderConfig.getPath()));
            URI uri = resourceURL.toURI();
            File file = new File(uri);
            CSVReader csvReader = new CSVReaderBuilder
                    (new FileReader(file)).withCSVParser(
                            new CSVParserBuilder().withSeparator(';').build()).withSkipLines(1)
                    .build();

            List<String[]> lines = csvReader.readAll();

            for (String[] line : lines) {
                MovieRequest movieRequest = new MovieRequest();
                movieRequest.setYear(line[0]);
                movieRequest.setTitle(line[1]);
                movieRequest.setStudios(line[2]);
                movieRequest.setProducers(line[3]);
                movieRequest.setWinner(line[4]);
                movieService.createMovie(MovieBuilder.createMovieFromRequest(movieRequest));
            }
        } catch (IOException | CsvException | MovieAlreadyExistsException | InvalidWinnerOptionException | URISyntaxException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}

package br.com.golden_awards_worst_movies.application.service.impl;

import br.com.golden_awards_worst_movies.application.config.CsvReaderConfig;
import br.com.golden_awards_worst_movies.domain.service.MovieService;
import br.com.golden_awards_worst_movies.application.service.PipelineDataReader;
import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;
import br.com.golden_awards_worst_movies.domain.exception.MovieAlreadyExistsException;
import br.com.golden_awards_worst_movies.domain.model.Movie;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

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
        try (InputStream inputStream = getClass().getResourceAsStream(csvReaderConfig.getPath())) {
            if (inputStream != null) {
                try {
                    File file = streamToFile(inputStream);
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
                        movieService.createMovie(new Movie.Builder().fromRequest(movieRequest).build());
                    }
                }  catch (IOException | CsvException | MovieAlreadyExistsException | InvalidWinnerOptionException e) {
                    LOG.error(e.getMessage());
                    throw new RuntimeException();
                }
            } else {
                LOG.error("Resource not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOG.info("FULLY REGISTERED CSV MOVIES");
    }

    private static File streamToFile(InputStream inputStream) throws IOException {
        File tempFile = File.createTempFile("movies.csv", ".tmp");
        tempFile.deleteOnExit();

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return tempFile;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void postConstruct(){
        readMoviesFromCsv();
    }
}

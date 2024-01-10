package br.com.golden_awards_worst_movies.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CsvDataLoader {

    private final RestTemplate restTemplate;
    private final Environment environment;

    @Autowired
    public CsvDataLoader(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void executeOnSchedule() {
        String hostname = environment.getProperty("server.host", "localhost");
        int port = environment.getProperty("server.port", Integer.class, 8080);

        String url = "http://" + hostname + ":" + port + "/api/v1/csvReader";
        restTemplate.getForObject(url, String.class);
    }
}
package br.com.golden_awards_worst_movies.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoldenAwardsWorstMoviesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldenAwardsWorstMoviesApplication.class, args);
    }

}

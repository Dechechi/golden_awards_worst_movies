package br.com.golden_awards_worst_movies.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "reader.csv")
@Data
public class CsvReaderConfig {

    private String path;

}

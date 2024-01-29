package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.infrastructure.entity.EntityScanMarker;
import br.com.golden_awards_worst_movies.infrastructure.repository.RepositoryScanMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = EntityScanMarker.class)
@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
public class RepositoryFactory {
}
package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.entity.EntityScanMarker;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.*;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.MovieRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.ProducerAwardRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.ProducerRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.StudioRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = EntityScanMarker.class)
@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
public class RepositoryFactory {

    @Bean
    MovieRepositoryI movieRepositoryI(MovieSpringRepository movieSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new MovieRepository(movieSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerAwardRepositoryI producerAwardRepositoryI(ProducerAwardSpringRepository producerAwardSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerAwardRepository(producerAwardSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerRepositoryI producerRepositoryI(ProducerSpringRepository producerSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerRepository(producerSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    StudioRepositoryI studioRepositoryI(StudioSpringRepository studioSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new StudioRepository(studioSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

}
package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.application.service.MovieService;
import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.application.service.impl.MovieServiceImpl;
import br.com.golden_awards_worst_movies.application.service.impl.ProducerAwardServiceImpl;
import br.com.golden_awards_worst_movies.application.service.impl.ProducerServiceImpl;
import br.com.golden_awards_worst_movies.application.service.impl.StudioServiceImpl;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.MovieSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerAwardSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.MovieRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.ProducerAwardRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.ProducerRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.impl.StudioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Bean
    StudioRepositoryI studioRepositoryI(StudioSpringRepository studioSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new StudioRepository(studioSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    StudioService studioService(StudioRepositoryI studioRepositoryI){
        return new StudioServiceImpl(studioRepositoryI);
    }

    @Bean
    ProducerRepositoryI producerRepositoryI(ProducerSpringRepository producerSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerRepository(producerSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerService producerService(ProducerRepositoryI producerRepositoryI, ProducerAwardService producerAwardService){
        return new ProducerServiceImpl(producerRepositoryI, producerAwardService);
    }

    @Bean
    ProducerAwardRepositoryI producerAwardRepositoryI(ProducerAwardSpringRepository producerAwardSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerAwardRepository(producerAwardSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerAwardService producerAwardService(ProducerAwardRepositoryI producerAwardRepositoryI){
        return new ProducerAwardServiceImpl(producerAwardRepositoryI);
    }

    @Bean
    MovieRepositoryI movieRepositoryI(MovieSpringRepository movieSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new MovieRepository(movieSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    MovieService movieService(MovieRepositoryI movieRepositoryI, StudioService studioService, ProducerService producerService){
        return new MovieServiceImpl(movieRepositoryI, studioService, producerService);
    }

}

package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.application.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.application.service.ProducerService;
import br.com.golden_awards_worst_movies.application.service.StudioService;
import br.com.golden_awards_worst_movies.application.service.impl.ProducerAwardServiceImpl;
import br.com.golden_awards_worst_movies.application.service.impl.ProducerServiceImpl;
import br.com.golden_awards_worst_movies.application.service.impl.StudioServiceImpl;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerAwardSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.ProducerSpringRepository;
import br.com.golden_awards_worst_movies.infrastructure.repository.StudioSpringRepository;
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
    StudioService studioService(StudioSpringRepository studioSpringRepository, StudioRepositoryI studioRepositoryI, DomainToEntityMapper domainToEntityMapper){
        return new StudioServiceImpl(studioSpringRepository,studioRepositoryI,domainToEntityMapper);
    }

    @Bean
    ProducerRepositoryI producerRepositoryI(ProducerSpringRepository producerSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerRepository(producerSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerService producerService(ProducerSpringRepository producerSpringRepository, ProducerRepositoryI producerRepositoryI, ProducerAwardService producerAwardService, DomainToEntityMapper domainToEntityMapper, EntityToDomainMapper entityToDomainMapper){
        return new ProducerServiceImpl(producerSpringRepository, producerRepositoryI, producerAwardService, domainToEntityMapper, entityToDomainMapper);
    }

    @Bean
    ProducerAwardRepositoryI producerAwardRepositoryI(ProducerAwardSpringRepository producerAwardSpringRepository, EntityToDomainMapper entityToDomainMapper, DomainToEntityMapper domainToEntityMapper){
        return new ProducerAwardRepository(producerAwardSpringRepository, entityToDomainMapper, domainToEntityMapper);
    }

    @Bean
    ProducerAwardService producerAwardService(ProducerAwardSpringRepository producerAwardSpringRepository, ProducerAwardRepositoryI producerAwardRepositoryI, DomainToEntityMapper domainToEntityMapper, EntityToDomainMapper entityToDomainMapper){
        return new ProducerAwardServiceImpl(producerAwardSpringRepository, producerAwardRepositoryI, domainToEntityMapper, entityToDomainMapper);
    }

}

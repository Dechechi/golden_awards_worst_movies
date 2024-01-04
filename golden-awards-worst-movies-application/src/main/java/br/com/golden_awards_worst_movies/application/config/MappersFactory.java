package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.domain.mapper.DomainToResponseMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.DomainToEntityMapper;
import br.com.golden_awards_worst_movies.infrastructure.mapper.EntityToDomainMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersFactory {

    @Bean
    public DomainToEntityMapper movieToEntityMapper(){
        return new DomainToEntityMapper();
    }

    @Bean
    public EntityToDomainMapper entityToDomainMapper(){
        return new EntityToDomainMapper();
    }

    @Bean
    public DomainToResponseMapper domainToResponseMapper(){
        return new DomainToResponseMapper();
    }
}

package br.com.golden_awards_worst_movies.application.config;

import br.com.golden_awards_worst_movies.domain.service.MovieService;
import br.com.golden_awards_worst_movies.domain.service.ProducerAwardService;
import br.com.golden_awards_worst_movies.domain.service.ProducerService;
import br.com.golden_awards_worst_movies.domain.service.StudioService;
import br.com.golden_awards_worst_movies.domain.service.impl.MovieServiceImpl;
import br.com.golden_awards_worst_movies.domain.service.impl.ProducerAwardServiceImpl;
import br.com.golden_awards_worst_movies.domain.service.impl.ProducerServiceImpl;
import br.com.golden_awards_worst_movies.domain.service.impl.StudioServiceImpl;
import br.com.golden_awards_worst_movies.domain.repository.MovieRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerAwardRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.ProducerRepositoryI;
import br.com.golden_awards_worst_movies.domain.repository.StudioRepositoryI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Bean
    StudioService studioService(StudioRepositoryI studioRepositoryI){
        return new StudioServiceImpl(studioRepositoryI);
    }

    @Bean
    ProducerService producerService(ProducerRepositoryI producerRepositoryI, ProducerAwardService producerAwardService){
        return new ProducerServiceImpl(producerRepositoryI, producerAwardService);
    }

    @Bean
    ProducerAwardService producerAwardService(ProducerAwardRepositoryI producerAwardRepositoryI){
        return new ProducerAwardServiceImpl(producerAwardRepositoryI);
    }
    @Bean
    MovieService movieService(MovieRepositoryI movieRepositoryI, StudioService studioService, ProducerService producerService){
        return new MovieServiceImpl(movieRepositoryI, studioService, producerService);
    }

}

package br.com.golden_awards_worst_movies.domain.behavior;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.ProducerAward;

public class ProducerAwardBuilder {
    
    public static ProducerAward createAward(Producer producer, int interval, int previousWin, int followingWin){
        return new ProducerAward(producer, interval, previousWin, followingWin);
    } 
    
}

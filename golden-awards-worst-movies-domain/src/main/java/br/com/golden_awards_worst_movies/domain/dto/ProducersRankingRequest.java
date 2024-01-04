package br.com.golden_awards_worst_movies.domain.dto;

import br.com.golden_awards_worst_movies.domain.model.ProducerRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProducersRankingRequest {

    private List<ProducerRecord> min;
    private List<ProducerRecord> max;

}

package br.com.golden_awards_worst_movies.domain.dto;

import br.com.golden_awards_worst_movies.domain.model.Producer;
import br.com.golden_awards_worst_movies.domain.model.Studio;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private Long id;
    private int year;
    private String title;
    private List<Studio> studios;
    private List<Producer> producers;
    private boolean winner;

}

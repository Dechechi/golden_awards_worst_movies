package br.com.golden_awards_worst_movies.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    private String year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

}

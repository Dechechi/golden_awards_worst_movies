package br.com.golden_awards_worst_movies.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorBaseResponse {

    private String code;
    private String message;

}

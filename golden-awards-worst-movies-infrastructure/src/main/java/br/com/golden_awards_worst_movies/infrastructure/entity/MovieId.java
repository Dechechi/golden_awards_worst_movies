package br.com.golden_awards_worst_movies.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class MovieId implements Serializable {

    @Serial
    private static final long serialVersionUID = 5176921548979694525L;

    private Long id;
    private int releaseYear;
    private String title;

}

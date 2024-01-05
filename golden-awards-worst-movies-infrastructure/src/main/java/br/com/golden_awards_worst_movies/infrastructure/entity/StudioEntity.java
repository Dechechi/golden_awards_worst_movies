package br.com.golden_awards_worst_movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "Studios")
@Data
@NoArgsConstructor
public class StudioEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2186836468432369509L;

    @Id
    private String name;

    public StudioEntity(String name) {
        this.name = name;
    }
}

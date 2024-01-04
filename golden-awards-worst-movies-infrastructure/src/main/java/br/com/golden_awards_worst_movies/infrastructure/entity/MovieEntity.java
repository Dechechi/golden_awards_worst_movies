package br.com.golden_awards_worst_movies.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Movie")
@Data
public class MovieEntity implements Serializable {

    private static final long serialVersionUID = -1392891313995202719L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int releaseYear;
    @Column(nullable = false, name = "title")
    private String title;
    @JsonIgnoreProperties("movies")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "studio_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private Set<StudioEntity> studios;
    @JsonIgnoreProperties("movies")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "producer_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private Set<ProducerEntity> producers;
    @Column(name = "winner")
    private boolean winner;

}

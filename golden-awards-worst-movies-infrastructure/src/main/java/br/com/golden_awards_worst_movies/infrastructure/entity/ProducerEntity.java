package br.com.golden_awards_worst_movies.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Producers")
@Data
@NoArgsConstructor
public class ProducerEntity implements Serializable {

    private static final long serialVersionUID = 8936374795080469338L;

    @Id
    private String name;
//    @JsonIgnoreProperties("producers")
//    @ManyToMany(mappedBy = "producers")
//    private Set<MovieEntity> movies;

    public ProducerEntity(String name) {
        this.name = name;
    }

//    @PreRemove
//    private void removeMovieAssociation(){
//        for (MovieEntity movie : movies){
//            movie.getProducers().remove(this);
//        }
//    }
}

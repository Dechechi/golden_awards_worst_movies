package br.com.golden_awards_worst_movies.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Studios")
@Data
@NoArgsConstructor
public class StudioEntity implements Serializable {

    private static final long serialVersionUID = 2186836468432369509L;

    @Id
    private String name;
//    @JsonIgnoreProperties("studios")
//    @ManyToMany(mappedBy = "studios")
//    private Set<MovieEntity> movies;

    public StudioEntity(String name) {
        this.name = name;
    }

//    @PreRemove
//    private void removeMovieAssociation(){
//        for (MovieEntity movie : movies){
//            movie.getStudios().remove(this);
//        }
//    }
}

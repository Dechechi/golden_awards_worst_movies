package br.com.golden_awards_worst_movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Producers")
@Data
@NoArgsConstructor
public class ProducerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8936374795080469338L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String awardYears;

    @ManyToMany(mappedBy = "producers")
    private Set<MovieEntity> movies;

    public ProducerEntity(String name) {
        this.name = name;
    }

    public ProducerEntity(String name, String awardYears) {
        this.name = name;
        this.awardYears = awardYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducerEntity that = (ProducerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

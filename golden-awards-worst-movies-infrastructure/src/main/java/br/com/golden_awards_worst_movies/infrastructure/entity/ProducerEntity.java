package br.com.golden_awards_worst_movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Producers")
@Data
@NoArgsConstructor
public class ProducerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8936374795080469338L;

    @Id
    private String name;

//    @OneToOne
//    private ProducerRecordEntity producerRecord;

    public ProducerEntity(String name) {
        this.name = name;
    }
}

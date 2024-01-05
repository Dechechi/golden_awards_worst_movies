package br.com.golden_awards_worst_movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ProducerRecords")
@Data
@NoArgsConstructor
public class ProducerRecordEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2017771806997095144L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "producer_name")
    private ProducerEntity producer;
    private int intervalTime;
    private int previousWin;
    private int followingWin;

}

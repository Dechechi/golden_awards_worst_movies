package br.com.golden_awards_worst_movies.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

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
    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private ProducerEntity producer;
    private int intervalTime;
    private int previousWin;
    private int followingWin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducerRecordEntity that = (ProducerRecordEntity) o;
        return previousWin == that.previousWin && followingWin == that.followingWin && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, previousWin, followingWin);
    }
}

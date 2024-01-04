package br.com.golden_awards_worst_movies.domain.model;

public record ProducerRecord(String producer, int interval, int previousWin, int followingWin) {
}

package br.com.golden_awards_worst_movies.domain.model;

import java.util.List;

public record Producer(Long id, String name, List<Integer> awardYears) {
}
package br.com.golden_awards_worst_movies.domain.model;

import java.util.List;

public class Producer {

    private Long id;
    private String name;
    private List<Integer> awardYears;

    public Producer() {
    }

    public Producer(Long id, String name, List<Integer> awardYears) {
        this.id = id;
        this.name = name;
        this.awardYears = awardYears;
    }

    public Producer(String name, List<Integer> awardYears) {
        this.name = name;
        this.awardYears = awardYears;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getAwardYears() {
        return awardYears;
    }
}
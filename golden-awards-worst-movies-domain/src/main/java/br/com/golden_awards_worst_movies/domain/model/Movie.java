package br.com.golden_awards_worst_movies.domain.model;

import java.util.List;

public class Movie {

    private Long id;
    private int year;
    private String title;
    private List<Studio> studios;
    private List<Producer> producers;
    private boolean winner;

    public Movie() {
    }

    public Movie(int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public Movie(Long id, int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public boolean isWinner() {
        return winner;
    }
}
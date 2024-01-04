package br.com.golden_awards_worst_movies.domain.model;

import java.util.List;

public record Movie(Long id,int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {

    public Movie(int year, String title, List<Studio> studios, List<Producer> producers, boolean winner) {
        this(null,year,title,studios,producers,winner);
    }

}
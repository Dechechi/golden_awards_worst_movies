package br.com.golden_awards_worst_movies.domain.model;

import br.com.golden_awards_worst_movies.domain.dto.MovieRequest;
import br.com.golden_awards_worst_movies.domain.enums.WinnerBoolean;
import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;

import java.util.ArrayList;
import java.util.Arrays;
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

    private Movie(Builder builder) {
        id = builder.id;
        year = builder.year;
        title = builder.title;
        studios = builder.studios;
        producers = builder.producers;
        winner = builder.winner;
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

    public static final class Builder {
        private Long id;
        private int year;
        private String title;
        private List<Studio> studios;
        private List<Producer> producers;
        private boolean winner;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder copy(Movie original){
            this.id = original.getId();
            this.year = original.getYear();
            this.title = original.getTitle();
            this.studios = original.getStudios();
            this.producers = original.getProducers();
            this.winner = original.isWinner();
            return this;
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withYear(int val) {
            year = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withStudios(List<Studio> val) {
            studios = val;
            return this;
        }

        public Builder withProducers(List<Producer> val) {
            producers = val;
            return this;
        }

        public Builder withWinner(boolean val) {
            winner = val;
            return this;
        }

        public Builder fromRequestWithId(Long id, MovieRequest movieRequest) throws InvalidWinnerOptionException {
            return this.withId(id).withTitle(movieRequest.getTitle())
                    .withProducersFromString(movieRequest.getProducers(), movieRequest.getYear(), movieRequest.getWinner())
                    .withStudiosFromString(movieRequest.getStudios())
                    .withYear(Integer.parseInt(movieRequest.getYear()))
                    .withWinner(WinnerBoolean.valueFrom(movieRequest.getWinner()));
        }

        public Builder fromRequest(MovieRequest movieRequest) throws InvalidWinnerOptionException {
            return this.withTitle(movieRequest.getTitle())
                    .withProducersFromString(movieRequest.getProducers(), movieRequest.getYear(), movieRequest.getWinner())
                    .withStudiosFromString(movieRequest.getStudios())
                    .withYear(Integer.parseInt(movieRequest.getYear()))
                    .withWinner(WinnerBoolean.valueFrom(movieRequest.getWinner()));
        }

        public Builder withStudiosFromString(String val){
            studios = Arrays.stream(val.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> new Studio.Builder().withName(s).build()).toList();
            return this;
        }

        public Builder withProducersFromString(String producersS, String year, String winner) throws InvalidWinnerOptionException {
            List<Integer> years = new ArrayList<>();
            if(WinnerBoolean.valueFrom(winner)){
                years.add(Integer.parseInt(year));
            }
            producers = Arrays.stream(producersS.split("\\s*(,|\\band\\b)"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> new Producer.Builder().withName(s).withAwardYears(years).build()).toList();
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
package br.com.golden_awards_worst_movies.domain.model;

import java.util.List;

public class Producer {

    private Long id;
    private String name;
    private List<Integer> awardYears;

    public Producer() {
    }

    private Producer(Builder builder) {
        id = builder.id;
        name = builder.name;
        awardYears = builder.awardYears;
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

    public static final class Builder {
        private Long id;
        private String name;
        private List<Integer> awardYears;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withAwardYears(List<Integer> val) {
            awardYears = val;
            return this;
        }

        public Producer build() {
            return new Producer(this);
        }
    }
}
package br.com.golden_awards_worst_movies.domain.model;

public class Studio {

    private Long id;
    private String name;

    public Studio() {
    }

    private Studio(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static final class Builder {
        private Long id;
        private String name;

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

        public Studio build() {
            return new Studio(this);
        }
    }
}

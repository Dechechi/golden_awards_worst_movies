package br.com.golden_awards_worst_movies.domain.model;

public class Studio {

    private Long id;
    private String name;

    public Studio() {
    }

    public Studio(String name) {
        this.name = name;
    }

    public Studio(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

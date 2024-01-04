package br.com.golden_awards_worst_movies.domain.enums;

import br.com.golden_awards_worst_movies.domain.exception.MovieDontExistException;
import br.com.golden_awards_worst_movies.domain.exception.UnhandledBaseException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ErrorMapping {

    MOVIE_DOESNT_EXIST(MovieDontExistException.class,"100");

    private final Class clazz;
    private final String code;

    ErrorMapping(Class clazz, String code) {
        this.clazz = clazz;
        this.code = code;
    }

    public static ErrorMapping getByClass(Class clazz) throws UnhandledBaseException {
        return Stream.of(ErrorMapping.values()).filter(
                (errorMaping) -> errorMaping.getClazz().equals(clazz))
                .findFirst().orElseThrow(UnhandledBaseException::new);
    }
}

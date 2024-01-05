package br.com.golden_awards_worst_movies.domain.exception;

public class MovieAlreadyExistsException extends BaseException{

    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}

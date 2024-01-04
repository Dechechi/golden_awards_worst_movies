package br.com.golden_awards_worst_movies.domain.exception;

public class MovieDontExistException extends BaseException{
    public MovieDontExistException(String message) {
        super(message);
    }
}

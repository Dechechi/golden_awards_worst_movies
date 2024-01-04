package br.com.golden_awards_worst_movies.application.handler;

import br.com.golden_awards_worst_movies.domain.dto.ErrorBaseResponse;
import br.com.golden_awards_worst_movies.domain.enums.ErrorMapping;
import br.com.golden_awards_worst_movies.domain.exception.BaseException;
import br.com.golden_awards_worst_movies.domain.exception.UnhandledBaseException;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BussinessErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> handleError(BaseException ex, WebRequest request) {
        ErrorBaseResponse errorBaseResponse;
        try {
            errorBaseResponse = new ErrorBaseResponse(ErrorMapping.getByClass(ex.getClass()).getCode(), ex.getMessage());
        } catch (UnhandledBaseException e) {
            errorBaseResponse = new ErrorBaseResponse("300", ex.getMessage());
        }
        return handleExceptionInternal(ex, errorBaseResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

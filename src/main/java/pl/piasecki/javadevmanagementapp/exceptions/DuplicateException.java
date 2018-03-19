package pl.piasecki.javadevmanagementapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super();
    }

    public DuplicateException(String s) {
        super(s);
    }

    public DuplicateException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

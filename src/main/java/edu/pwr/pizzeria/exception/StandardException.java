package edu.pwr.pizzeria.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class StandardException {

    private final String message;
    private final String path;
    private final HttpStatus status;

    @JsonCreator
    public StandardException(
            @JsonProperty("message") String message,
            @JsonProperty("path") String path,
            @JsonProperty("status") HttpStatus status) {
        this.message = message;
        this.path = path;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

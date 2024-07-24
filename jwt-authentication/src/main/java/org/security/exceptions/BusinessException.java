package org.security.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends Exception implements BusinessExceptionPolicy {

    private final String title;
    private final String message;
    private final ExceptionSource source;
    private final HttpStatus httpStatus;

    public BusinessException(BusinessExceptionReason businessExceptionReason) {
        this.title = businessExceptionReason.getTitle();
        this.message = businessExceptionReason.getMessage();
        this.source = businessExceptionReason.getExceptionSource();
        this.httpStatus = businessExceptionReason.getHttpStatus();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}

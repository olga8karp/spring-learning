package org.security.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessExceptionReason implements BusinessExceptionPolicy {

    INVALID_REQUEST("Invalid request", "Failed to authorise due to empty request body: {}", HttpStatus.BAD_REQUEST, ExceptionSource.USERS),
    FAILED_TO_AUTHORIZE_USER("Unable to authorize user", "Failed to authorize with message: {}", HttpStatus.BAD_REQUEST, ExceptionSource.USERS),
    FAILED_TO_VALIDATE_USER_ROLE("Unable to validate user authority", "Failed to validate user role: {}", HttpStatus.BAD_REQUEST, ExceptionSource.USERS);

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private final String title;
    private final String message;
    private final HttpStatus status;
    private final ExceptionSource exceptionSource;
}

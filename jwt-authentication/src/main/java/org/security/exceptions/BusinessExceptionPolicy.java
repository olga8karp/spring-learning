package org.security.exceptions;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionPolicy {


    HttpStatus getHttpStatus();

    String getMessage();

}

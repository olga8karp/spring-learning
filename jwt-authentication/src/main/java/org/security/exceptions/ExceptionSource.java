package org.security.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionSource {

    USERS("Users");

    private final String source;
}

package org.security.logs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogOutput {

    REQUEST_TO_AUTHORISE("Request received to {}"),
    SUCCESSFUL_AUTHORISATION("Successfully authorised user ");

    private final String message;

}

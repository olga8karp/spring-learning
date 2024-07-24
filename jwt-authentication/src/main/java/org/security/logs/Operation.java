package org.security.logs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operation {

    AUTHORIZE("authorize");

    private final String value;

}

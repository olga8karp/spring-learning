package org.security.api;

import lombok.extern.slf4j.Slf4j;
import org.security.exceptions.BusinessException;
import org.security.exceptions.BusinessExceptionReason;
import org.security.logs.LogOutput;
import org.security.logs.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class AuthorizationController {

    @PostMapping("/login")
    public ResponseEntity<Void> authorizeUsers(@RequestBody String jsonObj) throws BusinessException {
        log.info(LogOutput.REQUEST_TO_AUTHORISE.getMessage(), Operation.AUTHORIZE.getValue());
        if (jsonObj.isEmpty() || jsonObj.isBlank()) {
            throw new BusinessException(BusinessExceptionReason.INVALID_REQUEST);
        }
        log.info(LogOutput.SUCCESSFUL_AUTHORISATION.getMessage());
        return ResponseEntity.ok().build();
    }

}

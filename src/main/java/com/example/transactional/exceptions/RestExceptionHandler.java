package com.example.transactional.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ErrorResponse result = new ErrorResponse("Call again /people API and expect 'failed' to be false, since transaction has been rollbacked");
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(MyCheckedException.class)
    protected ResponseEntity<Object> handleMyCheckedException(MyCheckedException ex) {
        ErrorResponse result = new ErrorResponse("Call again /people API and expect 'failed' to be true: transaction has not been rollbacked: YUPPI YEAH");
        return ResponseEntity.badRequest().body(result);
    }

}

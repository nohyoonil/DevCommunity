package com.yoon.devcommunity.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> customExceptionHandler(CustomException customException) {
        ErrorCode errorCode = customException.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> conversionExceptionHandler(MethodArgumentTypeMismatchException e) {
        ErrorCode errorCode = ErrorCode.INVALID_DATA_INPUT;

        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(Exception e) {

        log.error("INTERNAL_SERVER_ERROR " + e.getMessage());

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode.getMessage()));
    }
}

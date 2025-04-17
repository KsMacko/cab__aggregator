package com.internship.passengerservice.utils.exceptions;

import lombok.RequiredArgsConstructor;
import org.hibernate.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.ERROR_INVALID_INPUT;
import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.ERROR_NOT_READABLE;
import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.TYPE_MISMATCH;
import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.UNKNOWN_ERROR;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseValidationException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> {
                    Object[] arguments = fieldError.getArguments();
                    return messageSource.getMessage(
                            fieldError.getDefaultMessage(),
                            arguments,
                            LocaleContextHolder.getLocale());
                })
                .toList();
        return new ResponseEntity<>(new BaseValidationException(messageSource.getMessage(
                ERROR_INVALID_INPUT.getCode(),
                null,
                LocaleContextHolder.getLocale()), errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler( ResourceNotFoundException.class)
    public ResponseEntity<BaseException> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new BaseException(messageSource.getMessage(
                ex.getMessage(),
                null,
                LocaleContextHolder.getLocale())), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<BaseException> handleInvalidInputExceptions(Exception ex) {
        return new ResponseEntity<>(new BaseException(messageSource.getMessage(
                ex.getMessage(),
                null,
                LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(messageSource.getMessage(
                        ERROR_NOT_READABLE.getCode(),
                        null,
                        LocaleContextHolder.getLocale()));
    }
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchException(TypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(messageSource.getMessage(
                        TYPE_MISMATCH.getCode(),
                        null,
                        LocaleContextHolder.getLocale()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseException> handleUnexpectedException(Exception ex) {
        log.error("Unexpected exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseException(messageSource.getMessage(
                        UNKNOWN_ERROR.getCode(),
                        null,
                        LocaleContextHolder.getLocale())));
    }
}

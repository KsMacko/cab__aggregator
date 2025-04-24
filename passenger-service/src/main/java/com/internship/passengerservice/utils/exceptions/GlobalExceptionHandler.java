package com.internship.passengerservice.utils.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    private static final ObjectMapper objectMapper = new ObjectMapper();

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
    public ResponseEntity<BaseException> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseException(messageSource.getMessage(
                        ERROR_NOT_READABLE.getCode(),
                        null,
                        LocaleContextHolder.getLocale())));
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseException> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseException(messageSource.getMessage(
                        TYPE_MISMATCH.getCode(),
                        null,
                        LocaleContextHolder.getLocale())));
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
    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<BaseValidationException> handleFeignExceptionBadRequest(FeignException.BadRequest exception) {
        try {
            String responseBody = exception.contentUTF8();
            BaseValidationException validationException = objectMapper.readValue(responseBody, BaseValidationException.class);

            return ResponseEntity.status(exception.status()).body(validationException);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseValidationException(messageSource.getMessage(
                    ERROR_INVALID_INPUT.getCode(),
                    null,
                    LocaleContextHolder.getLocale()), List.of(exception.getLocalizedMessage())), HttpStatus.BAD_REQUEST);
        }
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseException> handleFeignException(FeignException exception) {
        try {
            String responseBody = exception.contentUTF8();
            BaseException validationException = objectMapper.readValue(responseBody, BaseException.class);

            return ResponseEntity.status(exception.status()).body(validationException);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseException(messageSource.getMessage(
                    ERROR_INVALID_INPUT.getCode(),
                    null,
                    LocaleContextHolder.getLocale())), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.bhagwati.ContractManagement.exception;

import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * The type Exceptions handler.
 *
 * @author Akash Thomas
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(500).success(false)
                .error(HttpStatus.INTERNAL_SERVER_ERROR.toString()).message(e.getMessage()).build());
    }

    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(HttpMessageNotReadableException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        List<String> field = Arrays.asList(e.getLocalizedMessage().split("\""));
        String msg;
        if (field.size() > 1) {
            msg = MessageConstant.INVALID_INPUT_FIELD + field.get(1);
        } else {
            msg = MessageConstant.INVALID_REQUEST;
        }
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .error(HttpStatus.BAD_REQUEST.name()).message(msg).build());
    }

    /**
     * Duplicate exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<ResponseDto> duplicateExceptionClassHandler(DuplicateKeyException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .message(e.getMessage()).error(HttpStatus.BAD_REQUEST.getReasonPhrase()).build());
    }


    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(CustomExceptions e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(e.getStatusCode()).success(false)
                .message(e.getCustomMessage()).error(e.getMessage()).build());
    }

    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(MethodArgumentNotValidException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));

        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .message(HttpStatus.BAD_REQUEST.name()).error(errors).build());
    }

    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(AccessDeniedException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(401).success(false)
                .error(HttpStatus.UNAUTHORIZED.name()).message(HttpStatus.UNAUTHORIZED.name()).build());
    }


    /**
     * Exception class handler response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(MismatchedInputException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .error(HttpStatus.BAD_REQUEST.name()).message(MessageConstant.SOMETHING_WENT_WRONG).build());
    }


    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(InvalidDataAccessApiUsageException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .message(HttpStatus.BAD_REQUEST.name()).error(e.getLocalizedMessage()).build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(DataIntegrityViolationException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .error(HttpStatus.BAD_REQUEST.name()).message(e.getCause().getCause().getMessage().contains("duplicate") ? "Duplicate record. Data already exist." : "Data violation. Please check the inputs.").build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(IllegalArgumentException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(400).success(false)
                .error(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build());
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(ExecutionException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(500).success(false)
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name()).error(MessageConstant.SOMETHING_WENT_WRONG).build());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDto> exceptionClassHandler(NullPointerException e) {
        log.error(e.getLocalizedMessage() +
                " : " + e.getStackTrace()[0].getFileName() +
                " : " + e.getStackTrace()[0].getMethodName() +
                " : " + e.getStackTrace()[0].getLineNumber());
        return ResponseEntity.ok(ResponseDto.builder().statusCode(500).success(false)
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name()).message(e.getLocalizedMessage()).build());
    }

}

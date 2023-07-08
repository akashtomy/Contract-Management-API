package com.bhagwati.ContractManagement.exception;

import lombok.Data;

import java.util.List;

/**
 * The type Custom exceptions.
 *
 * @author Akash Thomas
 * @author Dipak Desai
 */
@Data
public class CustomExceptions extends RuntimeException {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Status code.
     */
    private Integer statusCode = 400;

    /**
     * The Custom message.
     */
    private String customMessage = "Bad Request";

    private List<Object> errorObj;

//    private String code;

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     */
    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     * @param statusCode   the status code
     */
    public CustomExceptions(String errorMessage, Integer statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new Custom exceptions.
     *
     * @param errorMessage the error message
     * @param statusCode   the status code
     * @param message      the message
     */
    public CustomExceptions(String message, Integer statusCode, String errorMessage) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.customMessage = message;
    }


    public CustomExceptions(String message, Integer statusCode, String errorMessage, List<Object> errorObj) {
        super(errorMessage);
        this.statusCode = statusCode;
        this.customMessage = message;
        this.errorObj = errorObj;
//        this.code = code;
    }

}

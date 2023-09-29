package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Response dto.
 *
 * @author Akash Thomas
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Status code.
     */
    private Integer statusCode;

    /**
     * The Is error.
     */
    private Boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * The Error message.
     */
    private transient Object error;

    /**
     * The Data.
     */
    private transient Object data;

    public ResponseDto(Integer statusCode, Boolean success, String message) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
    }

    public ResponseDto(Integer statusCode, Boolean success, String message, Object data) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

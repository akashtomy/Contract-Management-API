package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.bhagwati.ContractManagement.entity.BankDetail} entity
 *
 * @author Akash Thomas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Account number.
     */
    private String accountNumber;
    /**
     * The Ifsc.
     */
    private String ifsc;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Comments.
     */
    private String comments;

}
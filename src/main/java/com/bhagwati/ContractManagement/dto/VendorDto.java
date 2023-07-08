package com.bhagwati.ContractManagement.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.bhagwati.ContractManagement.entity.Vendor} entity
 *
 * @author Akash Thomas.
 */
@Data
public class VendorDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Pan.
     */
    private String pan;
    /**
     * The Aadhar.
     */
    private String aadhar;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Bank.
     */
    private BankDetailDto bank;

}
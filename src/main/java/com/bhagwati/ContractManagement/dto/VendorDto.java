package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.bhagwati.ContractManagement.entity.Vendor} entity
 *
 * @author Akash Thomas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String vendorId;
    /**
     * The Name.
     */
    private String vendorName;
    /**
     * The Pan.
     */
    private String pan;
    /**
     * The Aadhar.
     */
    private String aadhar;

    /**
     * The Address.
     */
    private String address;
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

    /**
     * The Agreement.
     */
    private AgreementDto agreement;

}
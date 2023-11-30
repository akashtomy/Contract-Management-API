package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Transaction dto.
 *
 * @author Akash Thomas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto extends AuditableDto implements Serializable {
    /**
     * The Transaction id.
     */
    private String transactionId;
    /**
     * The Agreement id.
     */
    /**
     * The Vendor id.
     */
    private String agreementVendorId;
    /**
     * The Amount.
     */
    private Integer amount;
    /**
     * The Bill amount.
     */
    private Integer billAmount;
    /**
     * The Gross amount.
     */
    private Integer grossAmount;

    /**
     * The Comments.
     */
    private String comments;
    /**
     * The Net amount.
     */
    private Integer netAmount;
    /**
     * The Type.
     */
    private String type;
    /**
     * The File id.
     */
    private String fileId;
    /**
     * The Bill date.
     */
    private LocalDateTime billDate;
    /**
     * The Bill number.
     */
    private String billNumber;
    /**
     * The Agreement.
     */
    private AgreementDto agreement;
    /**
     * The Vendor.
     */
    private VendorDto vendor;

}
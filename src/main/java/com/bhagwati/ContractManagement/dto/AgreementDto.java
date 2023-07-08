package com.bhagwati.ContractManagement.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.bhagwati.ContractManagement.entity.Agreement} entity
 *
 * @author Akash Thomas.
 */
@Data
public class AgreementDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Loa date.
     */
    private LocalDateTime loaDate;
    /**
     * The Ca value.
     */
    private BigDecimal caValue;
    /**
     * The Location.
     */
    private String location;
    /**
     * The Status.
     */
    private String status;
    /**
     * The Description.
     */
    private String description;
    /**
     * The End date.
     */
    private LocalDateTime endDate;
 
    /**
     * The Vendor.
     */
    private VendorDto vendor;
}
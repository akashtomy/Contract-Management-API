package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Vendor mapping dto.
 *
 * @author Akash Thomas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorMappingDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Vendor.
     */
    private VendorDto vendor ;
    /**
     * The Agreement.
     */
    private AgreementDto agreement;


}
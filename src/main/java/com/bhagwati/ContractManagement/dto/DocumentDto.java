package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * The type Document dto.
 *
 * @author Akash Thomas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto extends AuditableDto implements Serializable {
    /**
     * The Id.
     */
    private String id;
    /**
     * The Type.
     */
    private String type;
    /**
     * The Url.
     */
    private String url;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Data.
     */
    private byte[] data;
    /**
     * The Agreement id.
     */
    private String agreementId;
    /**
     * The Vendor id.
     */
    private String vendorId;

    /**
     * The Files.
     */
    private MultipartFile[] files;

}
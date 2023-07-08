package com.bhagwati.ContractManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditableDto {
    /**
     * The Created by.
     */
    private String createdBy;
    /**
     * The Create datetime.
     */
    private LocalDateTime createDatetime;
    /**
     * The Modified datetime.
     */
    private LocalDateTime modifiedDatetime;
    /**
     * The Modified by.
     */
    private String modifiedBy;
    /**
     * The Version.
     */
    private Integer version;
}

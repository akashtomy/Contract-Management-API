package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

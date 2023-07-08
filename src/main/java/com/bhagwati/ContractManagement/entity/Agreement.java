package com.bhagwati.ContractManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type Agreement.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agreement")
public class Agreement {
    /**
     * The Id.
     */
    @Id
    @Column(name = "agreement_id", nullable = false)
    private String id;

    /**
     * The Name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The Loa date.
     */
    @Column(name = "loa_date", nullable = false)
    private LocalDateTime loaDate;

    /**
     * The Ca value.
     */
    @Column(name = "ca_value", nullable = false)
    private BigDecimal caValue;

    /**
     * The Location.
     */
    @Column(name = "location")
    private String location;

    /**
     * The Status.
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * The Description.
     */
    @Column(name = "description")
    private String description;

    /**
     * The End date.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * The Created by.
     */
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    /**
     * The Create datetime.
     */
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createDatetime;

    /**
     * The Modified datetime.
     */
    @Column(name = "modified_datetime", nullable = false)
    private LocalDateTime modifiedDatetime;

    /**
     * The Modified by.
     */
    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;

    /**
     * The Version.
     */
    @Column(name = "version")
    private Integer version;

    /**
     * The Vendor.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;


}
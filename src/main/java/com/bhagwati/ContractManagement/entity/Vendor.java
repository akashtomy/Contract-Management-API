package com.bhagwati.ContractManagement.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * The type Vendor.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "vendors")
public class Vendor {
    /**
     * The Id.
     */
    @Id
    @Column(name = "vendor_id", nullable = false)
    private String id;

    /**
     * The Name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The Pan.
     */
    @Column(name = "pan", nullable = false)
    private String pan;

    /**
     * The Aadhar.
     */
    @Column(name = "aadhar")
    private String aadhar;

    /**
     * The Phone.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The Email.
     */
    @Column(name = "email")
    private String email;

    /**
     * The Bank.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankDetail bank;

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

}
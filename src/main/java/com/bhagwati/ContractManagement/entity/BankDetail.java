package com.bhagwati.ContractManagement.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The type Bank detail.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bank_details")
public class BankDetail {
    /**
     * The Id.
     */
    @Id
    @Column(name = "bank_id", nullable = false)
    private String id;

    /**
     * The Account number.
     */
    @Column(name = "account_number")
    private String accountNumber;

    /**
     * The Ifsc.
     */
    @Column(name = "ifsc")
    private String ifsc;

    /**
     * The Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The Comments.
     */
    @Column(name = "comments")
    private String comments;

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
    @Column(name = "version", nullable = false)
    private Integer version;


}
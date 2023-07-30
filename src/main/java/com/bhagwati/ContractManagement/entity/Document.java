package com.bhagwati.ContractManagement.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * The type Document.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "documents")
public class Document {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "document_id", nullable = false)
    private String id;

    /**
     * The Type.
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * The Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The Data.
     */
    @Column(name = "data")
    private byte[] data;

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
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * The Agreement id.
     */
    @Column(name = "agreement_id")
    private String agreementId;

    /**
     * The Vendor id.
     */
    @Column(name = "vendor_id")
    private String vendorId;


}
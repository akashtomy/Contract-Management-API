package com.bhagwati.ContractManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The type Vendor.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vendors")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Vendor {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
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
     * The Address.
     */
    @Column(name = "address")
    private String address;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private BankDetail bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    /**
     * The Created by.
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The Create datetime.
     */
    @CreatedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "create_datetime", nullable = false)
    private LocalDateTime createDatetime;

    /**
     * The Modified datetime.
     */
    @LastModifiedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "modified_datetime", nullable = false)
    private LocalDateTime modifiedDatetime;

    /**
     * The Modified by.
     */
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * The Version.
     */
    @Version
    @Column(name = "version")
    private Integer version;

}
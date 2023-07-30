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
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "url")
    private String url;

    /**
     * The Data.
     */
    @Column(name = "data")
    private byte[] data;

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
    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;

    /**
     * The Modified datetime.
     */
    @LastModifiedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "modified_datetime")
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
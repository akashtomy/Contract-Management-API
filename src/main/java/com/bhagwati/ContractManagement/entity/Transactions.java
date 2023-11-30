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
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Transactions.
 *
 * @author Akash Thomas.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transactions  {
    /**
     * The Transaction id.
     */
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    /**
     * The Agreement id.
     */
    @Column(name = "agreement_vendor_id")
    private String agreementVendorId;


    /**
     * The Amount.
     */
    @Column(name = "amount")
    private Integer amount;

    /**
     * The Type.
     */
    @Column(name = "type")
    private String type;

    /**
     * The File id.
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * The Bill date.
     */
    @Column(name = "bill_date")
    private LocalDateTime billDate;

    /**
     * The Bill number.
     */
    @Column(name = "bill_number")
    private String billNumber;

    /**
     * The Bill amount.
     */
    @Column(name = "bill_amount")
    private Integer billAmount;

    /**
     * The Gross amount.
     */
    @Column(name = "gross_amount")
    private Integer grossAmount;

    /**
     * The Description.
     */
    @Column(name = "comments")
    private String comments;

    /**
     * The Net amount.
     */
    @Column(name = "net_amount")
    private Integer netAmount;

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
    @Column(name = "version")
    @Version
    private Integer version;

}

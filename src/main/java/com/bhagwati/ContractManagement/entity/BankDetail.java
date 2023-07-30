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

import javax.persistence.*;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
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
    @Column(name = "version", nullable = false)
    private Integer version;

}
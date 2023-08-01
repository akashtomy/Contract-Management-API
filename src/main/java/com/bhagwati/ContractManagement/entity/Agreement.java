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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
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
    private Integer version;

    /**
     * The Vendor.
     */
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "agreement")
    @Fetch(FetchMode.SUBSELECT)
    private List<AgreementVendorMapping> vendorMappings = new ArrayList<>();


}
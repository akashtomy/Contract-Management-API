package com.bhagwati.ContractManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Agreement vendor mapping.
 *
 * @author Akash Thomas.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agreement_vendor_mapping")
public class AgreementVendorMapping {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * The Agreement id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    /**
     * The Vendor id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public static List<Selection<?>> getSelections(Root<AgreementVendorMapping> from) {
        List<Selection<?>> paths = new ArrayList<>();
        paths.add(from.get("vendor"));
        paths.add(from.get("vendor").get("name"));
        paths.add(from.get("vendor").get("email"));
        paths.add(from.get("vendor").get("status"));
        paths.add(from.get("agreement").get("name"));
        paths.add(from.get("agreement").get("location"));
        paths.add(from.get("agreement").get("status"));
        paths.add(from.get("agreement").get("loaDate"));
        paths.add(from.get("agreement").get("endDate"));
        paths.add(from.get("agreement").get("createDatetime"));
        paths.add(from.get("agreement").get("modifiedDatetime"));
        return paths;
    }
}
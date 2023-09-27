package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Agreement;
import com.bhagwati.ContractManagement.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Vendors repository.
 *
 * @author Akash Thomas.
 */
public interface VendorsRepository extends JpaRepository<Vendor, String>, JpaSpecificationExecutor<Vendor> {
    List<Vendor> findByVendorIdIn(List<String> vendorIds);

    @Query("select vendor from Vendor vendor"
            + " inner join AgreementVendorMapping  vm on vm.vendor.vendorId = vendor.vendorId"
            + " inner join Agreement agreement on vm.agreement.id = agreement.id"
            + " where UPPER(vendor.vendorName) LIKE UPPER(concat('%', concat(?1, '%')))"
            + " OR UPPER(vendor.address) LIKE UPPER(concat('%', concat(?1, '%'))) OR UPPER(vendor.phone) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(vendor.aadhar) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(vendor.email) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(agreement.location) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(agreement.description) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(agreement.name) LIKE UPPER(concat('%', concat(?1, '%')))")
    Page<Vendor> search(String keyword, Pageable pageable);
}
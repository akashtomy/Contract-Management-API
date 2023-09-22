package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * The interface Vendors repository.
 *
 * @author Akash Thomas.
 */
public interface VendorsRepository extends JpaRepository<Vendor, String>, JpaSpecificationExecutor<Vendor> {
    List<Vendor> findByVendorIdIn(List<String> vendorIds);
}
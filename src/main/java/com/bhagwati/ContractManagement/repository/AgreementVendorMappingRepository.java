package com.bhagwati.ContractManagement.repository;


import com.bhagwati.ContractManagement.entity.AgreementVendorMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * The interface Agreement vendor mapping repository.
 *
 * @author Akash Thomas.
 */
@Repository
public interface AgreementVendorMappingRepository extends JpaRepository<AgreementVendorMapping, String>, JpaSpecificationExecutor<AgreementVendorMapping> {

}
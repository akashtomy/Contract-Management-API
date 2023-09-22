package com.bhagwati.ContractManagement.repository;


import com.bhagwati.ContractManagement.entity.Agreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Agreement repository.
 *
 * @author Akash Thomas.
 */
@Repository
public interface AgreementRepository extends JpaRepository<Agreement, String>, JpaSpecificationExecutor<Agreement> {

    @Query("select agreement from Agreement agreement"
            + " inner join agreement.vendorMappings vm on vm.agreement.id = agreement.id"
            + " inner join vm.vendor vendor on vm.vendor.vendorId = vendor.vendorId"
            + " where UPPER(agreement.name) LIKE UPPER(concat('%', concat(?1, '%')))"
            + " OR UPPER(agreement.location) LIKE UPPER(concat('%', concat(?1, '%'))) OR UPPER(agreement.description) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(agreement.status) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(vendor.email) LIKE UPPER(concat('%', concat(?1, '%')))"
            + "OR UPPER(vendor.vendorName) LIKE UPPER(concat('%', concat(?1, '%')))")
    Page<Agreement> search(String keyword, Pageable pageable);

}
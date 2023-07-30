package com.bhagwati.ContractManagement.repository;


import com.bhagwati.ContractManagement.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * The interface Agreement repository.
 *
 * @author Akash Thomas.
 */
@Repository
public interface AgreementRepository extends JpaRepository<Agreement, String>, JpaSpecificationExecutor<Agreement> {
}
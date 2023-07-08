package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Bank details repository.
 *
 * @author Akash Thomas.
 */
public interface BankDetailsRepository extends JpaRepository<BankDetail, String>, JpaSpecificationExecutor<BankDetail> {
}
package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Transactions repository.
 *
 * @author Akash Thomas.
 */
public interface TransactionsRepository extends JpaRepository<Transactions, String>, JpaSpecificationExecutor<Transactions> {
    /**
     * Find by id in list.
     *
     * @param transactionIds the vendor ids
     * @return the list
     */
    List<Transactions> findByTransactionIdIn(List<String> transactionIds);

    @Query("select trans from Transactions trans inner join AgreementVendorMapping avm on avm.id = trans.agreementVendorId " +
            "inner join Agreement ag on ag.id = avm.agreement.id where ag.id = :agreementId")
    List<Transactions> findByAgreementId(String agreementId);

    @Query("select trans from Transactions trans inner join AgreementVendorMapping avm on avm.id = trans.agreementVendorId " +
            "inner join Vendor ven on ven.vendorId = avm.vendor.vendorId where ven.vendorId = :vendorId")
    List<Transactions> findByVendorId(String vendorId);
}
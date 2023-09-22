package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}
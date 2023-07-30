package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * The interface Documents repository.
 *
 * @author Akash Thomas.
 */
public interface DocumentsRepository extends JpaRepository<Document, String>, JpaSpecificationExecutor<Document> {
    /**
     * Find by vendor id list.
     *
     * @param vedorId the vedor id
     * @return the list
     */
    List<Document> findByVendorId(String vedorId);

    /**
     * Find by agreement id list.
     *
     * @param agreementId the agreement id
     * @return the list
     */
    List<Document> findByAgreementId(String agreementId);

}
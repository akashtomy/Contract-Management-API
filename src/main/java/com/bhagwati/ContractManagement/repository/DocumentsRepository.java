package com.bhagwati.ContractManagement.repository;

import com.bhagwati.ContractManagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Documents repository.
 *
 * @author Akash Thomas.
 */
public interface DocumentsRepository extends JpaRepository<Document, String>, JpaSpecificationExecutor<Document> {
}
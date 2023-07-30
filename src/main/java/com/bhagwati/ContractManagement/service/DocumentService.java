package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.DocumentDto;
import com.bhagwati.ContractManagement.entity.Document;
import com.bhagwati.ContractManagement.exception.CustomExceptions;
import com.bhagwati.ContractManagement.mapper.DocumentMapper;
import com.bhagwati.ContractManagement.repository.DocumentsRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Document service.
 *
 * @author Akash Thomas.
 */
@Service
public class DocumentService {

    /**
     * The Root.
     */
    private final Path root = Paths.get("uploads");
    /**
     * The Documents repository.
     */
    @Autowired
    private DocumentsRepository documentsRepository;

    /**
     * The Document mapper.
     */
    @Autowired
    private DocumentMapper documentMapper;

    /**
     * Load document resource.
     *
     * @param filename the filename
     * @return the resource
     */
    public Resource loadDocument(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    /**
     * Gets document by id.
     *
     * @param documentId the document id
     * @return the document by id
     */
    public DocumentDto getDocumentById(String documentId) {
        Document document = documentsRepository.findById(documentId).orElseThrow(() -> new RuntimeException("Data not found"));
        return documentMapper.convertEntityToDto(document);
    }


    public List<DocumentDto> getDocumentsByVendorId(String documentId) {
        List<Document> documents = documentsRepository.findByVendorId(documentId);
        if(!CollectionUtils.isEmpty(documents)){
            return documentMapper.convertEntityListToDtoList(documents);
        }
        throw new CustomExceptions("Document not found", 404);
    }

    public List<DocumentDto> getDocumentsByAgreementId(String documentId) {
        List<Document> documents = documentsRepository.findByAgreementId(documentId);
        if(!CollectionUtils.isEmpty(documents)) {
            return documentMapper.convertEntityListToDtoList(documents);
        }
        throw new CustomExceptions("Document not found", 404);
    }
    /**
     * Save document object.
     *
     * @param file the file
     * @return the object
     * @throws IOException the io exception
     */
    public DocumentDto saveDocument(MultipartFile file, String vendorId, String agreementId) throws IOException {
        String filename = file.getOriginalFilename();
        Path path = this.root.resolve(filename);
        Document document = new Document();
        if (!StringUtils.isEmpty(vendorId))
            document.setVendorId(vendorId);
        if (!StringUtils.isEmpty(agreementId))
            document.setAgreementId(agreementId);
        document.setName(filename);
        document.setType(FilenameUtils.getExtension(filename));
        document.setUrl(path.toUri().toString());
        Files.copy(file.getInputStream(), path);
        Document savedDocument = documentsRepository.save(document);
        return documentMapper.convertEntityToDto(savedDocument);
    }

    /**
     * Delete vendor boolean.
     *
     * @param vendorId the vendor id
     * @return the boolean
     */
    public boolean deleteDocument(String vendorId) throws IOException {
        Document doc = documentsRepository.findById(vendorId).orElseThrow(() -> new RuntimeException("File not found"));
        Path path = this.root.resolve(doc.getName());
        FileSystemUtils.deleteRecursively(path);
        documentsRepository.deleteById(vendorId);
        return true;
    }

    public Object saveDocuments(DocumentDto documentDto) throws IOException {
        List<DocumentDto> documents = new ArrayList<>();
        for (MultipartFile file : documentDto.getFiles()) {
            documents.add(saveDocument(file, documentDto.getVendorId(), documentDto.getAgreementId()));
        }
        return documents;
    }
}

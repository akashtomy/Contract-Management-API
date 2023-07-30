package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.DocumentDto;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * The type Document controller.
 *
 * @author Akash Thomas.
 */
@RestController
@RequestMapping(APIConstant.DOCUMENTS)
public class DocumentController {

    /**
     * The Document service.
     */
    @Autowired
    private DocumentService documentService;


    /**
     * Gets document by id.
     *
     * @param id the id
     * @return the document by id
     */
    @GetMapping(APIConstant.ID)
    public ResponseDto getDocumentById(@PathVariable String id) {
        return ResponseDto.builder().data(documentService.getDocumentById(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets documents by vendor id.
     *
     * @param id the id
     * @return the documents by vendor id
     */
    @GetMapping("/vendor/{id}")
    public ResponseDto getDocumentsByVendorId(@PathVariable String id) {
        return ResponseDto.builder().data(documentService.getDocumentsByVendorId(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets documents by agreement id.
     *
     * @param id the id
     * @return the documents by agreement id
     */
    @GetMapping("/agreement/{id}")
    public ResponseDto getDocumentsByAgreementId(@PathVariable String id) {
        return ResponseDto.builder().data(documentService.getDocumentsByAgreementId(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }


    /**
     * Delete agreement response dto.
     *
     * @param id the id
     * @return the response dto
     * @throws IOException the io exception
     */
    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteAgreement(@PathVariable String id) throws IOException {
        return ResponseDto.builder().data(documentService.deleteDocument(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Save documents response dto.
     *
     * @param documentDto the document dto
     * @return the response dto
     * @throws IOException the io exception
     */
    @PostMapping
    public ResponseDto saveDocuments(DocumentDto documentDto) throws IOException {
        return ResponseDto.builder().data(documentService.saveDocuments(documentDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


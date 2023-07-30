package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(APIConstant.DOCUMENTS)
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @GetMapping(APIConstant.ID)
    public ResponseDto getAgreement(@PathVariable String id) {
        return ResponseDto.builder().data(documentService.loadDocument(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }


    @PostMapping
    public ResponseDto saveDocument(@RequestBody MultipartFile multipartFile) throws IOException {
        return ResponseDto.builder().data(documentService.saveDocument(multipartFile)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }


    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteAgreement(@PathVariable String id) {
        return ResponseDto.builder().data(documentService.deleteVendor(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


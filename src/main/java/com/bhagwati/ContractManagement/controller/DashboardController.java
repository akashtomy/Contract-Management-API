package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.AgreementDto;
import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.dto.SearchFilterPageRequest;
import com.bhagwati.ContractManagement.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Dashboard controller.
 *
 * @author Akash Thomas.
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    /**
     * The Agreement service.
     */
    @Autowired
    private AgreementService agreementService;

    /**
     * Gets agreements.
     *
     * @return the agreements
     */
    @GetMapping
    public ResponseDto getAgreements() {
        return ResponseDto.builder().statusCode(200).success(true).data(agreementService.getAgreementDetails()).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets agreement.
     *
     * @param id the id
     * @return the agreement
     */
    @GetMapping(APIConstant.ID)
    public ResponseDto getAgreement(@PathVariable String id) {
        return ResponseDto.builder().statusCode(200).success(true).data(agreementService.getAgreementDetailsById(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Search agreement response dto.
     *
     * @param pageableRequestDto the pageable request dto
     * @return the response dto
     */
    @GetMapping(APIConstant.SEARCH)
    public ResponseDto searchAgreement(PageableRequestDto pageableRequestDto) {
        return ResponseDto.builder().statusCode(200).success(true).data(agreementService.searchAgreementDetails(pageableRequestDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

}


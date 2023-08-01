package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.AgreementDto;
import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Agreement controller.
 *
 * @author Akash Thomas.
 */
@RestController
@RequestMapping(APIConstant.AGREEMENTS)
public class AgreementController {

    /**
     * The Agreement service.
     */
    @Autowired
    private AgreementService agreementService;

    /**
     * Gets agreement.
     *
     * @return the agreement
     */
    @GetMapping
    public ResponseDto getAgreements() {
        return ResponseDto.builder().statusCode(200).data(agreementService.getAgreementDetails()).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets agreement.
     *
     * @param id the id
     * @return the agreement
     */
    @GetMapping(APIConstant.ID)
    public ResponseDto getAgreement(@PathVariable String id) {
        return ResponseDto.builder().statusCode(200).data(agreementService.getAgreementDetailsById(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Search agreement string.
     *
     * @param pageableRequestDto the pageable request dto
     * @return the string
     */
    @GetMapping(APIConstant.SEARCH)
    public ResponseDto searchAgreement(PageableRequestDto pageableRequestDto) {
        return ResponseDto.builder().statusCode(200).data(agreementService.searchAgreementDetails(pageableRequestDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Save agreement string.
     *
     * @param agreementDto the agreement dto
     * @return the string
     */
    @PostMapping
    public ResponseDto saveAgreement(@RequestBody AgreementDto agreementDto) {
        return ResponseDto.builder().statusCode(200).data(agreementService.saveAgreementDetails(agreementDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Update agreement string.
     *
     * @param agreementDto the agreement dto
     * @return the string
     */
    @PutMapping
    public ResponseDto updateAgreement(@RequestBody AgreementDto agreementDto) {
        return ResponseDto.builder().data(agreementService.updateAgreementDetails(agreementDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Delete agreement string.
     *
     * @param id the id
     * @return the string
     */
    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteAgreement(@PathVariable String id) {
        return ResponseDto.builder().data(agreementService.deleteAgreementDetails(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


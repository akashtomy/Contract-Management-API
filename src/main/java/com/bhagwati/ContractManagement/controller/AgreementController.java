package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.AgreementDto;
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
        return ResponseDto.builder().data(agreementService.getAgreementDetails()).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    @GetMapping(APIConstant.ID)
    public ResponseDto getAgreement(@PathVariable String id) {
        return ResponseDto.builder().data(agreementService.getAgreementDetailsById(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Search agreement string.
     *
     * @return the string
     */
    @GetMapping(APIConstant.SEARCH)
    public ResponseDto searchAgreement() {
        return ResponseDto.builder().data(agreementService.searchAgreementDetails()).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Save agreement string.
     *
     * @return the string
     */
    @PostMapping
    public ResponseDto saveAgreement(@RequestBody AgreementDto agreementDto) {
        return ResponseDto.builder().data(agreementService.saveAgreementDetails(agreementDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Update agreement string.
     *
     * @return the string
     */
    @PutMapping
    public ResponseDto updateAgreement(@RequestBody AgreementDto agreementDto) {
        return ResponseDto.builder().data(agreementService.updateAgreementDetails(agreementDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Delete agreement string.
     *
     * @return the string
     */
    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteAgreement(@PathVariable String id) {
        return ResponseDto.builder().data(agreementService.deleteAgreementDetails(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


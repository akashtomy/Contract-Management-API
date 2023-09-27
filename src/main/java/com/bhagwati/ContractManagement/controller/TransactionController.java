package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.dto.TransactionDto;
import com.bhagwati.ContractManagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Transaction controller.
 *
 * @author Akash Thomas.
 */
@RestController
@RequestMapping(APIConstant.TRANSACTIONS)
public class TransactionController {

    /**
     * The Transaction service.
     */
    @Autowired
    private TransactionService transactionService;

    /**
     * Gets transactions.
     *
     * @return the transactions
     */
    @GetMapping
    public ResponseDto getTransactions() {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.getVendorDetails()).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets transaction by id.
     *
     * @param id the id
     * @return the transaction by id
     */
    @GetMapping(APIConstant.ID)
    public ResponseDto getTransactionById(@PathVariable String id) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.getVendorById(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }


    @GetMapping(APIConstant.AGREEMENT_AGREEMENT_ID)
    public ResponseDto getTransactionByAgreementId(@PathVariable String agreementId) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.getTransactionByAgreementId(agreementId)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    @GetMapping(APIConstant.VENDOR_VENDOR_ID)
    public ResponseDto getTransactionByVendorId(@PathVariable String vendorId) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.getTransactionByVendorId(vendorId)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Search transaction response dto.
     *
     * @param pageableRequestDto the pageable request dto
     * @return the response dto
     */
    @GetMapping(APIConstant.SEARCH)
    public ResponseDto searchTransaction(PageableRequestDto pageableRequestDto) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.searchTransactions(pageableRequestDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Save transaction response dto.
     *
     * @param transactionDto the transaction dto
     * @return the response dto
     */
    @PostMapping
    public ResponseDto saveTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.saveVendor(transactionDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Update transaction response dto.
     *
     * @param transactionDto the transaction dto
     * @return the response dto
     */
    @PutMapping
    public ResponseDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.updateVendor(transactionDto)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Delete transaction response dto.
     *
     * @param id the id
     * @return the response dto
     */
    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteTransaction(@PathVariable String id) {
        return ResponseDto.builder().success(true).statusCode(200).data(transactionService.deleteVendor(id)).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


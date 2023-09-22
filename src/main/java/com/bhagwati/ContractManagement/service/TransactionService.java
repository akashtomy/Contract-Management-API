package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.PageableResponse;
import com.bhagwati.ContractManagement.dto.TransactionDto;
import com.bhagwati.ContractManagement.entity.Transactions;
import com.bhagwati.ContractManagement.mapper.TransactionMapper;
import com.bhagwati.ContractManagement.repository.TransactionsRepository;
import com.bhagwati.ContractManagement.utils.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Vendors service.
 *
 * @author Akash Thomas.
 */
@Service
public class TransactionService {

    /**
     * The Vendors repository.
     */
    @Autowired
    private TransactionsRepository transactionsRepository;

    /**
     * The Vendor mapper.
     */
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CommonUtils commonUtils;

    /**
     * Gets agreement details.
     *
     * @return the agreement details
     */
    public List<TransactionDto> getVendorDetails() {
        return transactionMapper.convertEntityListToDtoList(transactionsRepository.findAll());
    }

    /**
     * Gets agreement details by id.
     *
     * @param agreementId the agreement id
     * @return the agreement details by id
     */
    public TransactionDto getVendorById(String agreementId) {
        Transactions transactions = transactionsRepository.findById(agreementId).orElseThrow(() -> new RuntimeException("Data not found"));
        return transactionMapper.convertEntityToDto(transactions);
    }

    /**
     * Save agreement details vendor dto.
     *
     * @param vendorDto the vendor dto
     * @return the vendor dto
     */
    public TransactionDto saveVendor(TransactionDto vendorDto) {
        return transactionMapper.convertEntityToDto(transactionsRepository.save(transactionMapper.convertDtoToEntity(vendorDto)));
    }

    /**
     * Update agreement details vendor dto.
     *
     * @param transactionDto the vendor dto
     * @return the vendor dto
     */
    public TransactionDto updateVendor(TransactionDto transactionDto) {
        return transactionMapper.convertEntityToDto(transactionsRepository.save(transactionMapper.convertDtoToEntity(transactionDto)));
    }

    /**
     * Delete agreement details boolean.
     *
     * @param vendorId the vendor id
     * @return the boolean
     */
    public boolean deleteVendor(String vendorId) {
        if (transactionsRepository.existsById(vendorId)) {
            transactionsRepository.deleteById(vendorId);
            return true;
        }
        return false;
    }


    /**
     * Search agreement details list.
     *
     * @return the list
     */
    public PageableResponse<TransactionDto> searchVendors(PageableRequestDto pageableRequestDto) {
        Pageable pageable = PageRequest.of(ObjectUtils.defaultIfNull(pageableRequestDto.getPageNo(), 1) - 1,
                ObjectUtils.defaultIfNull(pageableRequestDto.getPageSize(), 5),
                Sort.by(commonUtils.getPaginationOrders(pageableRequestDto.getSortBy())));
        PageableResponse<TransactionDto> pageableResponse = new PageableResponse<>();
//        GenricSpecification genericSpecification = new GenericSpecification<>(pageableRequestDto.getFilter()).build(pageableRequestDto.getSearchKey());
        Page<Transactions> transactionsPages = transactionsRepository.findAll(pageable);
        List<TransactionDto> transactionDtos = transactionMapper.convertEntityListToDtoList(transactionsPages.getContent());
        return pageableResponse.convert(new PageImpl<>(transactionDtos, pageable, transactionsPages.getTotalElements()));
    }
}
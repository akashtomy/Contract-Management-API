package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.AgreementDto;
import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.PageableResponse;
import com.bhagwati.ContractManagement.entity.Agreement;
import com.bhagwati.ContractManagement.entity.AgreementVendorMapping;
import com.bhagwati.ContractManagement.entity.Vendor;
import com.bhagwati.ContractManagement.mapper.AgreementMapper;
import com.bhagwati.ContractManagement.repository.AgreementRepository;
import com.bhagwati.ContractManagement.repository.VendorsRepository;
import com.bhagwati.ContractManagement.utils.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Agreement service.
 *
 * @author Akash Thomas.
 */
@Service
public class AgreementService {

    @Autowired
    private AgreementRepository agreementRepository;

    /**
     * The Agreement mapper.
     */
    @Autowired
    private AgreementMapper agreementMapper;


    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private VendorsRepository vendorsRepository;

    /**
     * Get agreement details list.
     *
     * @return the list
     */
    public List<AgreementDto> getAgreementDetails() {
        return agreementMapper.convertEntityListToDtoList(agreementRepository.findAll());
    }

    /**
     * Get agreement details by id agreement dto.
     *
     * @param agreementId the agreement id
     * @return the agreement dto
     */
    public AgreementDto getAgreementDetailsById(String agreementId) {
        return agreementMapper.convertEntityToDto(agreementRepository.findById(agreementId).orElseThrow(() -> new RuntimeException("Data not found")));
    }

    /**
     * Save agreement details agreement dto.
     *
     * @param agreementDto the agreement dto
     * @return the agreement dto
     */
    @Transactional
    public AgreementDto saveAgreementDetails(AgreementDto agreementDto) {
        List<String> vendorIds = agreementDto.getVendors().stream().map(vendorDto -> vendorDto.getId()).collect(Collectors.toList());
        Agreement agreement = agreementMapper.convertDtoToEntity(agreementDto);
        List<Vendor> vendors = vendorsRepository.findByIdIn(vendorIds);
        List<AgreementVendorMapping> agreementVendorMappings = new ArrayList<>();
        for (Vendor vendor : vendors) {
            AgreementVendorMapping agreementVendorMapping = new AgreementVendorMapping();
            agreementVendorMapping.setAgreement(agreement);
            agreementVendorMapping.setVendor(vendor);
            agreementVendorMappings.add(agreementVendorMapping);
        }
        agreement.setVendorMappings(agreementVendorMappings);
        Agreement savedAgreement = agreementRepository.save(agreement);
        return agreementMapper.convertEntityToDto(savedAgreement);
    }

    /**
     * Update agreement details agreement dto.
     *
     * @param agreementDto the agreement dto
     * @return the agreement dto
     */
    public AgreementDto updateAgreementDetails(AgreementDto agreementDto) {
        return agreementMapper.convertEntityToDto(agreementRepository.save(agreementMapper.convertDtoToEntity(agreementDto)));
    }

    /**
     * Delete agreement details boolean.
     *
     * @param agreementId the agreement id
     * @return the boolean
     */
    public boolean deleteAgreementDetails(String agreementId) {
        if (agreementRepository.existsById(agreementId)) {
            agreementRepository.deleteById(agreementId);
            return true;
        }
        return false;
    }


    /**
     * Search agreement details list.
     *
     * @return the list
     */
    public PageableResponse<AgreementDto> searchAgreementDetails(PageableRequestDto pageableRequestDto) {
        Pageable pageable = PageRequest.of(ObjectUtils.defaultIfNull(pageableRequestDto.getPageNo(), 1) - 1,
                ObjectUtils.defaultIfNull(pageableRequestDto.getPageSize(), 5),
                Sort.by(commonUtils.getPaginationOrders(pageableRequestDto.getSortBy())));
        Page<Agreement> pages = agreementRepository.findAll(pageable);
        List<AgreementDto> agreementDtos = agreementMapper.convertEntityListToDtoList(pages.getContent());
        PageableResponse<AgreementDto> pageableResponse = new PageableResponse<>();
        return pageableResponse.convert(new PageImpl<>(agreementDtos, pageable, pages.getTotalElements()));
    }
}

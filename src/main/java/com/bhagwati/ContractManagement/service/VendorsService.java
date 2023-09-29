package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.PageableResponse;
import com.bhagwati.ContractManagement.dto.VendorDto;
import com.bhagwati.ContractManagement.entity.Vendor;
import com.bhagwati.ContractManagement.mapper.VendorMapper;
import com.bhagwati.ContractManagement.repository.VendorsRepository;
import com.bhagwati.ContractManagement.utils.CommonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
public class VendorsService {

    /**
     * The Vendors repository.
     */
    @Autowired
    private VendorsRepository vendorsRepository;

    /**
     * The Vendor mapper.
     */
    @Autowired
    private VendorMapper vendorMapper;

    @Autowired
    private CommonUtils commonUtils;

    /**
     * Gets agreement details.
     *
     * @return the agreement details
     */
    public List<VendorDto> getVendorDetails() {
        return vendorMapper.convertEntityListToDtoList(vendorsRepository.findAll());
    }

    /**
     * Gets agreement details by id.
     *
     * @param agreementId the agreement id
     * @return the agreement details by id
     */
    public VendorDto getVendorById(String agreementId) {
        Vendor vendor = vendorsRepository.findById(agreementId).orElseThrow(() -> new RuntimeException("Data not found"));
        return vendorMapper.convertEntityToDto(vendor);
    }

    /**
     * Save agreement details vendor dto.
     *
     * @param vendorDto the vendor dto
     * @return the vendor dto
     */
    public VendorDto saveVendor(VendorDto vendorDto) {
        return vendorMapper.convertEntityToDto(vendorsRepository.save(vendorMapper.convertDtoToEntity(vendorDto)));
    }

    /**
     * Update agreement details vendor dto.
     *
     * @param vendorDto the vendor dto
     * @return the vendor dto
     */
    public VendorDto updateVendor(VendorDto vendorDto) {
        return vendorMapper.convertEntityToDto(vendorsRepository.save(vendorMapper.convertDtoToEntity(vendorDto)));
    }

    /**
     * Delete agreement details boolean.
     *
     * @param vendorId the vendor id
     * @return the boolean
     */
    public boolean deleteVendor(String vendorId) {
        if (vendorsRepository.existsById(vendorId)) {
            vendorsRepository.deleteById(vendorId);
            return true;
        }
        return false;
    }


    /**
     * Search agreement details list.
     *
     * @return the list
     */
    public PageableResponse<VendorDto> searchVendors(PageableRequestDto pageableRequestDto) {
        Pageable pageable = PageRequest.of(ObjectUtils.defaultIfNull(pageableRequestDto.getPageNo(), 1) - 1,
                ObjectUtils.defaultIfNull(pageableRequestDto.getPageSize(), 5),
                Sort.by(commonUtils.getPaginationOrders(pageableRequestDto.getSortBy())));
        PageableResponse<VendorDto> pageableResponse = new PageableResponse<>();
//        genericSpecification = new GenericSpecification<>(pageableRequestDto.getFilter()).build(pageableRequestDto.getSearchKey());
        Page<Vendor> vendorPages;
        if (StringUtils.isEmpty(pageableRequestDto.getSearchKey())) {
            vendorPages = vendorsRepository.findAll(pageable);
        } else {
            vendorPages = vendorsRepository.search(pageableRequestDto.getSearchKey(), pageable);
        }
        List<VendorDto> vendorDtos = vendorMapper.convertEntityListToDtoList(vendorPages.getContent());
        return pageableResponse.convert(new PageImpl<>(vendorDtos, pageable, vendorPages.getTotalElements()));
    }
}

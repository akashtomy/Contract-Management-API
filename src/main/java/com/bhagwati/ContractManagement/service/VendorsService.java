package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.VendorDto;
import com.bhagwati.ContractManagement.entity.Vendor;
import com.bhagwati.ContractManagement.mapper.VendorMapper;
import com.bhagwati.ContractManagement.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VendorDto> searchAgreementDetails() {
        return vendorMapper.convertEntityListToDtoList(vendorsRepository.findAll());
    }
}

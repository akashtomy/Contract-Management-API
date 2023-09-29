package com.bhagwati.ContractManagement.controller;

import com.bhagwati.ContractManagement.constant.APIConstant;
import com.bhagwati.ContractManagement.constant.MessageConstant;
import com.bhagwati.ContractManagement.dto.PageableRequestDto;
import com.bhagwati.ContractManagement.dto.ResponseDto;
import com.bhagwati.ContractManagement.dto.VendorDto;
import com.bhagwati.ContractManagement.service.VendorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Vendor controller.
 *
 * @author Akash Thomas.
 */
@RestController
@RequestMapping(APIConstant.VENDORS)
public class VendorController {

    /**
     * The Vendors service.
     */
    @Autowired
    private VendorsService vendorsService;

    /**
     * Gets agreements.
     *
     * @return the agreements
     */
    @GetMapping
    public ResponseDto getVendors() {
        return ResponseDto.builder().statusCode(200).data(vendorsService.getVendorDetails()).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Gets agreement.
     *
     * @param id the id
     * @return the agreement
     */
    @GetMapping(APIConstant.ID)
    public ResponseDto getVendorsById(@PathVariable String id) {
        return ResponseDto.builder().statusCode(200).data(vendorsService.getVendorById(id)).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Search agreement response dto.
     *
     * @return the response dto
     */
    @GetMapping(APIConstant.SEARCH)
    public ResponseDto searchVendors(PageableRequestDto pageableRequestDto) {
        return ResponseDto.builder().statusCode(200).data(vendorsService.searchVendors(pageableRequestDto)).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Save agreement response dto.
     *
     * @param vendorDto the vendor dto
     * @return the response dto
     */
    @PostMapping
    public ResponseDto saveVendors(@RequestBody VendorDto vendorDto) {
        return ResponseDto.builder().statusCode(200).data(vendorsService.saveVendor(vendorDto)).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Update agreement response dto.
     *
     * @param vendorDto the vendor dto
     * @return the response dto
     */
    @PutMapping
    public ResponseDto updateVendors(@RequestBody VendorDto vendorDto) {
        return ResponseDto.builder().statusCode(200).data(vendorsService.updateVendor(vendorDto)).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }

    /**
     * Delete agreement response dto.
     *
     * @param id the id
     * @return the response dto
     */
    @DeleteMapping(APIConstant.ID)
    public ResponseDto deleteVendor(@PathVariable String id) {
        return ResponseDto.builder().statusCode(200).data(vendorsService.deleteVendor(id)).success(true).error(null).message(MessageConstant.REQUEST_FULFILLED_SUCCESSFULLY).build();
    }
}


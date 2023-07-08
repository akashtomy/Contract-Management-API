package com.bhagwati.ContractManagement.service;

import com.bhagwati.ContractManagement.dto.AgreementDto;
import com.bhagwati.ContractManagement.entity.Agreement;
import com.bhagwati.ContractManagement.mapper.AgreementMapper;
import com.bhagwati.ContractManagement.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Get agreement details list.
     *
     * @return the list
     */
    public List<AgreementDto> getAgreementDetails(){
        return agreementMapper.convertEntityListToDtoList(agreementRepository.findAll());
    }

    /**
     * Get agreement details by id agreement dto.
     *
     * @param agreementId the agreement id
     * @return the agreement dto
     */
    public AgreementDto getAgreementDetailsById(String agreementId){
        Agreement agreement = agreementRepository.findById(agreementId).orElseThrow(() -> new RuntimeException("Data not found"));
        return agreementMapper.convertEntityToDto(agreement);
    }

    /**
     * Save agreement details agreement dto.
     *
     * @param agreementDto the agreement dto
     * @return the agreement dto
     */
    public AgreementDto saveAgreementDetails(AgreementDto agreementDto){
        return agreementMapper.convertEntityToDto(agreementRepository.save(agreementMapper.convertDtoToEntity(agreementDto)));
    }

    /**
     * Update agreement details agreement dto.
     *
     * @param agreementDto the agreement dto
     * @return the agreement dto
     */
    public AgreementDto updateAgreementDetails(AgreementDto agreementDto){
        return agreementMapper.convertEntityToDto(agreementRepository.save(agreementMapper.convertDtoToEntity(agreementDto)));
    }

    /**
     * Delete agreement details boolean.
     *
     * @param agreementId the agreement id
     * @return the boolean
     */
    public boolean deleteAgreementDetails(String agreementId){
        if(agreementRepository.existsById(agreementId)) {
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
    public List<AgreementDto> searchAgreementDetails(){
        return agreementMapper.convertEntityListToDtoList(agreementRepository.findAll());
    }
}

package com.bhagwati.ContractManagement.mapper;

import com.bhagwati.ContractManagement.dto.AgreementDto;
import com.bhagwati.ContractManagement.dto.VendorMappingDto;
import com.bhagwati.ContractManagement.entity.Agreement;
import com.bhagwati.ContractManagement.entity.AgreementVendorMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * The interface Agreement mapper.
 *
 * @author Akash Thomas.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = VendorMapper.class)
public interface AgreementMapper {

    /**
     * Convert dto to entity agreement.
     *
     * @param dto the dto
     * @return the agreement
     */
    Agreement convertDtoToEntity(AgreementDto dto);

    /**
     * Convert entity to dto agreement dto.
     *
     * @param entity the entity
     * @return the agreement dto
     */
    AgreementDto convertEntityToDto(Agreement entity);

    /**
     * Convert entity list to dto list list.
     *
     * @param entity the entity
     * @return the list
     */
    List<AgreementDto> convertEntityListToDtoList(List<Agreement> entity);

    /**
     * Convert dto list to entity list list.
     *
     * @param dtos the dtos
     * @return the list
     */
    @InheritConfiguration(name = "convertEntityListToDtoList")
    List<Agreement> convertDtoListToEntityList(List<AgreementDto> dtos);

    @InheritConfiguration(name = "convertMappingEntityToDto")
    List<VendorMappingDto> convertMappingEntityListToDtoList(List<AgreementVendorMapping> entity);

    @Mapping(target = "agreement", ignore = true)
    VendorMappingDto convertMappingEntityToDto(AgreementVendorMapping entity);

}

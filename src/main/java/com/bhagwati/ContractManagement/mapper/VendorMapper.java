package com.bhagwati.ContractManagement.mapper;

import com.bhagwati.ContractManagement.dto.VendorDto;
import com.bhagwati.ContractManagement.entity.Vendor;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * The interface Vendor mapper.
 *
 * @author Akash Thomas.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VendorMapper {

    /**
     * Convert dto to entity vendor.
     *
     * @param dto the dto
     * @return the vendor
     */
    Vendor convertDtoToEntity(VendorDto dto);

    /**
     * Convert entity to dto vendor dto.
     *
     * @param entity the entity
     * @return the vendor dto
     */
    VendorDto convertEntityToDto(Vendor entity);

    /**
     * Convert entity list to dto list list.
     *
     * @param entity the entity
     * @return the list
     */
    List<VendorDto> convertEntityListToDtoList(List<Vendor> entity);

    /**
     * Convert dto list to entity list list.
     *
     * @param dtos the dtos
     * @return the list
     */
    @InheritConfiguration(name = "convertEntityListToDtoList")
    List<Vendor> convertDtoListToEntityList(List<VendorDto> dtos);


}

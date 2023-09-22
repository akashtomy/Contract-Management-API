package com.bhagwati.ContractManagement.mapper;

import com.bhagwati.ContractManagement.dto.TransactionDto;
import com.bhagwati.ContractManagement.entity.Transactions;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * The interface Transactions mapper.
 *
 * @author Akash Thomas.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    /**
     * Convert dto to entity Transactions.
     *
     * @param dto the dto
     * @return the Transactions
     */
    Transactions convertDtoToEntity(TransactionDto dto);

    /**
     * Convert entity to dto Transactions dto.
     *
     * @param entity the entity
     * @return the Transactions dto
     */
    TransactionDto convertEntityToDto(Transactions entity);

    /**
     * Convert entity list to dto list list.
     *
     * @param entity the entity
     * @return the list
     */
    List<TransactionDto> convertEntityListToDtoList(List<Transactions> entity);

    /**
     * Convert dto list to entity list list.
     *
     * @param dtos the dtos
     * @return the list
     */
    @InheritConfiguration(name = "convertEntityListToDtoList")
    List<Transactions> convertDtoListToEntityList(List<TransactionDto> dtos);


}

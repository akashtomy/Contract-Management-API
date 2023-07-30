package com.bhagwati.ContractManagement.mapper;

import com.bhagwati.ContractManagement.dto.DocumentDto;
import com.bhagwati.ContractManagement.entity.Document;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * The interface Document mapper.
 *
 * @author Akash Thomas.
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DocumentMapper {

    /**
     * Convert dto to entity document.
     *
     * @param dto the dto
     * @return the document
     */
    Document convertDtoToEntity(DocumentDto dto);

    /**
     * Convert entity to dto document dto.
     *
     * @param entity the entity
     * @return the document dto
     */
    DocumentDto convertEntityToDto(Document entity);

    /**
     * Convert entity list to dto list list.
     *
     * @param entity the entity
     * @return the list
     */
    List<DocumentDto> convertEntityListToDtoList(List<Document> entity);

    /**
     * Convert dto list to entity list list.
     *
     * @param dtos the dtos
     * @return the list
     */
    @InheritConfiguration(name = "convertEntityListToDtoList")
    List<Document> convertDtoListToEntityList(List<DocumentDto> dtos);


}

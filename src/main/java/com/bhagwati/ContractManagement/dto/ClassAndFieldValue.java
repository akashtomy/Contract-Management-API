package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Class and field value.
 *
 * @author Dipak Desai
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassAndFieldValue {

    /**
     * The Field class.
     */
    Class<?> fieldClass;
    /**
     * The Field value.
     */
    String fieldValue;

    Class<?> secondLevelClass;

    String secondLevelValue;
}

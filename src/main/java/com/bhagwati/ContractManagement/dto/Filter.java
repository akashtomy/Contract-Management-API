package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private String field;
    private String value;
    private ConditionOperator operator;
    private List<String> entityFieldValues;

}

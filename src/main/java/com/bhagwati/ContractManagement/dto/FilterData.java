package com.bhagwati.ContractManagement.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterData {
    private String field;
    private String fieldType;
    private String dataType;
    private String operators;
    private String fieldDesc;
    private String lookupCode;
    private String searchUrl;
    private String httpMethod;
    private String autoCompleteCode;
    private String autoCompleteDescription;
}

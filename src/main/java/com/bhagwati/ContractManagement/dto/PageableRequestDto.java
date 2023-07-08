package com.bhagwati.ContractManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRequestDto {
    Integer pageNo;
    Integer pageSize;

    String sortBy;
    String searchKey;
//    List<FilterParam> filter = new ArrayList<>();

}

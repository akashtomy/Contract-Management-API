package com.bhagwati.ContractManagement.dto;

import lombok.*;

import java.util.List;

/**
 * The type Search filter page request.
 *
 * @author Athrav Jaiswal
 * @author Dipak Desai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchFilterPageRequest extends PageableRequestDto {
    /**
     * The Filters.
     */
    List<Filter> filters;
}

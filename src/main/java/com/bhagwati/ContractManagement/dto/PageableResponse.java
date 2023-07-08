package com.bhagwati.ContractManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T> {

    private Integer totalPages;
    private Integer currentPage;
    private Long totalRecords;
    private List<T> data;

    public PageableResponse<T> convert(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber() + 1;
        this.totalRecords = page.getTotalElements();
        this.data = page.getContent();
        return this;
    }
}

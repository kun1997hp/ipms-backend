package com.viettel.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class DataTable {
    private final long totalElements;
    private final int totalPages;
    private final List<?> content;

    public DataTable(Page<?> page){
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        content = page.getContent();
    }
}

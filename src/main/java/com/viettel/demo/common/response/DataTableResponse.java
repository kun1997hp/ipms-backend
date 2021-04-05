package com.viettel.demo.common.response;

import com.viettel.demo.common.DataTable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DataTableResponse {
    private final String code;
    private final DataTable dataTable;
}

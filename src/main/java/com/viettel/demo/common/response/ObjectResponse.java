package com.viettel.demo.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectResponse {
    private final String code;
    private final Object object;
}

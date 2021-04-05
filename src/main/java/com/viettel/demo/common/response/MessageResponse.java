package com.viettel.demo.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageResponse {
    private List<String> message;

    public MessageResponse(String mess) {
        message = Collections.singletonList(mess);
    }
}
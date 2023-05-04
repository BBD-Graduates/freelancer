package com.fl.bid.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FlResponse<T> {
    private T response;
    private String message;
}

package com.fl.project.model;

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
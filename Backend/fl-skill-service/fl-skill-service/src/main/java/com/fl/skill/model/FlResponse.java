package com.fl.skill.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
public class FlResponse<T> {
    private T response;
    private String message;
}

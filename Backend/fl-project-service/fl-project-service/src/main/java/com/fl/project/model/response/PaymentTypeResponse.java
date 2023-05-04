package com.fl.project.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeResponse {
    private Integer paymentTypeId;
    private String paymentType;
    private String createdDate;
    
}

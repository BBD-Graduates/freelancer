package com.fl.project.service.serviceInterface;

import java.util.List;

import com.fl.project.model.Request.PaymentTypeRequest;
import com.fl.project.model.Response.PaymentTypeResponse;

public interface PaymentTypeService {
    String savePaymentType(PaymentTypeRequest project);
    List<PaymentTypeResponse> getAllPaymentType();
}

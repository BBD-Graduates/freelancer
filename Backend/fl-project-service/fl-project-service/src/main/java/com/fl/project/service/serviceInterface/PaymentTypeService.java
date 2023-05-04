package com.fl.project.service.serviceInterface;

import java.util.List;

import com.fl.project.model.request.PaymentTypeRequest;
import com.fl.project.model.response.PaymentTypeResponse;

public interface PaymentTypeService {
    String savePaymentType(PaymentTypeRequest project);
    List<PaymentTypeResponse> getAllPaymentType();
}

package com.fl.project.service.serviceInterface;

import java.util.List;

import com.fl.project.model.PaymentTypeRequest;
import com.fl.project.model.Response.PaymentTypeResponse;

public interface PaymentTypeService {
    String save(PaymentTypeRequest project);
    List<PaymentTypeResponse> getAll();
}
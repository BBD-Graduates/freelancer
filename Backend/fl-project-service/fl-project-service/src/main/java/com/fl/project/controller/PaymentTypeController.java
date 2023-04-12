package com.fl.project.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fl.project.model.FlResponse;
import com.fl.project.model.PaymentTypeRequest;
import com.fl.project.model.Response.PaymentTypeResponse;
import com.fl.project.service.serviceInterface.PaymentTypeService;
import com.fl.project.util.FlResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static com.fl.project.config.Constant.*;

@RestController
@RequestMapping("/paymentType")
@RequiredArgsConstructor
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeRepo;
    private final FlResponseUtil flResponseUtil;

    @GetMapping
    public ResponseEntity<FlResponse<List<PaymentTypeResponse>>> getAllPaymentType() {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, paymentTypeRepo.getAll(),
                    String.format("%s" + FETCHED_SUCCESSFULLY, PAYMENT_TYPE));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format(NO_RECORD_FOUND));
        }
    }

    @PostMapping
    public ResponseEntity<FlResponse<String>> addPaymentType(
            @Valid @RequestBody PaymentTypeRequest paymentTypeRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, paymentTypeRepo.save(paymentTypeRequest),
                    String.format("%s" + INSERTED_SUCCESSFULLY, PAYMENT_TYPE));

        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
            String.format("%s " + INSERTION_FAILED));
        }
    }

}

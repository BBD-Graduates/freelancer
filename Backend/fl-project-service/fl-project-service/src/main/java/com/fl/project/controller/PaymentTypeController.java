package com.fl.project.controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fl.project.model.FlResponse;
import com.fl.project.model.request.PaymentTypeRequest;
import com.fl.project.model.response.PaymentTypeResponse;
import com.fl.project.service.serviceInterface.PaymentTypeService;
import com.fl.project.util.FlResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static com.fl.project.config.Constant.*;

@RestController
@RequestMapping("/flp/projects/paymentType")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeRepo;
    private final FlResponseUtil flResponseUtil;

    @GetMapping
    public ResponseEntity<FlResponse<List<PaymentTypeResponse>>> getAllPaymentType() {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, paymentTypeRepo.getAllPaymentType(),
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
            return flResponseUtil.getResponseEntity(HttpStatus.OK, paymentTypeRepo.savePaymentType(paymentTypeRequest),
                    String.format("%s" + INSERTED_SUCCESSFULLY, PAYMENT_TYPE));

        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
            String.format("%s " + INSERTION_FAILED));
        }
    }

}

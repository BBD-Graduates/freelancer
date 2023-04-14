package com.fl.project.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentTypeRequest {
    @NotBlank(message = "Please enter paymentType")
    @NotNull(message = "Please enter paymentType")
    @Size(min=1 ,max = 12  )
    private String paymentType;
}

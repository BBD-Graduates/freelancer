package com.fl.bid.model.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BidRequest {
    @NotNull(message = "Please enter Project Id")
    private int projectId;
    @NotNull(message = "Please enter Freelancer Id")
    private int freelancerId;
    @NotNull(message = "Please enter Amount")
    private float amount;
    @NotNull(message = "Please enter Delivery Days")
    private int deliveryDays;
    @NotNull(message = "Please enter Description")
    @Size(min=20, message = "Description Should not be blank")
    private String description;
}

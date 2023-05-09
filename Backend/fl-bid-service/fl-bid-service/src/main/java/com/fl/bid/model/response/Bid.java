package com.fl.bid.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bid {
    private int bidId;
    private int projectId;
    private int freelancerId;
    private float amount;
    private int deliveryDays;
    private String description;
    private String createdDate;

}

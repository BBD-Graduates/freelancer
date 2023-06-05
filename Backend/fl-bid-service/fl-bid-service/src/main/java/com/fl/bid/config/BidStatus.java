package com.fl.bid.config;

import lombok.Getter;

@Getter
public enum BidStatus {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");


    BidStatus(String value) {
    }
}

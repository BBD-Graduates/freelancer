package com.fl.bid.config;

import lombok.Getter;

@Getter
public enum BidStatus {
//Enums for bid status
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),

//Enums for freelancer allocated projects status
    CURRENT_PROJECTS("CURRENT_PROJECTS"),
    COMPLETED_PROJECTS("COMPLETED_PROJECTS");


    BidStatus(String value) {
    }
}

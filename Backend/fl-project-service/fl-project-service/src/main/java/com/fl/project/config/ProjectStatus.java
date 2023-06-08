package com.fl.project.config;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    //project status
    POSTED("POSTED"),
    BID_IN_PROGRESS("BID_IN_PROGRESS"),
    IN_PROGRESS("IN_PROGRESS"),
    BID_COMPLETE("BID_COMPLETE"),
    COMPLETED("COMPLETED"),
    INACTIVE("INACTIVE"),

    //Bid status
    APPROVED("APPROVED");


    ProjectStatus(String value) {
    }
}

package com.fl.project.config;

import lombok.Getter;

@Getter
public enum ProjectStatus {

    POSTED("POSTED"),
    BID_IN_PROGRESS("BID_IN_PROGRESS"),
    IN_PROGRESS("IN_PROGRESS"),
    BID_COMPLETE("BID_COMPLETE"),
    COMPLETED("COMPLETED"),
    INACTIVE("INACTIVE");


    ProjectStatus(String value) {
    }
}

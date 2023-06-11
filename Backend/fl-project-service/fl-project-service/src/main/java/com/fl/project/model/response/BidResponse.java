package com.fl.project.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BidResponse {
    private int bidId;
    private int projectId;
    private int freelancerId;
    private int amount;
    private String status;
    private String description;
    private String createdDate;
}

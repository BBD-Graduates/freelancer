package com.fl.bid.model.response;
import com.fl.bid.model.response.Bid;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class ProjectBidResponse {
    private int projectId;
    @Builder.Default
    List<Bid> bids =new ArrayList<>();
}

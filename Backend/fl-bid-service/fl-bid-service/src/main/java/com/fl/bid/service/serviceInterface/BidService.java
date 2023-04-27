package com.fl.bid.service.serviceInterface;

import com.fl.bid.model.request.BidRequest;
import com.fl.bid.model.response.Bid;

import java.util.List;

public interface BidService {
    String insertBid(BidRequest bidRequest);
    String deleteBid(int bidId);
    String updateBid(BidRequest bidRequest,int bidId);
    List<Bid> getBids(Integer bidId, Integer projectId, Integer freelancerId);

}

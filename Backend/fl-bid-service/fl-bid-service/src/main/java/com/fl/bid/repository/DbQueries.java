package com.fl.bid.repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import java.util.List;
@Setter
@Getter
@Component
@RefreshScope
public class DbQueries {
    @Value("${db.queries.bid.insertBid}")
    private String addBid;
    @Value("${db.queries.bid.removeBid}")
    private String removeBid;
    @Value("${db.queries.bid.updateBid}")
    private String updateBid;
    @Value("${db.queries.bid.approveBid}")
    private String approveBid;
    @Value("${db.queries.bid.rejectBid}")
    private String rejectBid;
    @Value ("${db.queries.bid.selectAllBid}")
    private String allBid;
    @Value("${db.queries.bid.selectBidByBidId}")
    private String bidByBidId;
    @Value("${db.queries.bid.selectBidByProjectId}")
    private String bidByProjectId;
    @Value("${db.queries.bid.getBidsByFreelancerIdAndStatus}")
    private String bidsByFreelancerIdAndStatus;
    @Value("${db.queries.bid.getBidByFreelancerIdAndProjectId}")
    private String bidByFreelancerIdAndProjectId;

}

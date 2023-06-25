package com.fl.bid.service;

import com.fl.bid.client.BidProjectClient;
import com.fl.bid.client.BidUserClient;
import com.fl.bid.config.Constant;
import com.fl.bid.model.FlResponse;
import com.fl.bid.model.request.BidRequest;
import com.fl.bid.model.response.Bid;
import com.fl.bid.model.response.UserResponse;
import com.fl.bid.repository.DbQueries;
import com.fl.bid.service.serviceInterface.BidService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fl.bid.config.BidStatus.*;
import static com.fl.bid.config.Constant.*;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final DbQueries dbQueries;
    private final JdbcTemplate jdbcTemplate;
    private final BidUserClient bidUserClient;

    private final BidProjectClient bidProjectClient;


    @Override
    public String insertBid(BidRequest bidRequest) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddBid(),
                    bidRequest.getProjectId(), bidRequest.getFreelancerId(), bidRequest.getAmount(), bidRequest.getDeliveryDays(), bidRequest.getDescription());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String deleteBid(int bidId) {
        try {
            int deleteStatus = jdbcTemplate.update(dbQueries.getRemoveBid(), bidId);
            if (deleteStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.DELETION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String updateBid(BidRequest bidRequest, int bidId) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateBid(), bidRequest.getAmount(), bidRequest.getDeliveryDays(), bidRequest.getDescription(), bidId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.UPDATION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String updateBidStatusToApprove(Integer bidId, Integer projectId) {
        try {
             String bidApproveStatus = this.executeBidUpdateQueries(bidId,projectId);
             if(Objects.equals(bidApproveStatus, BID + APPROVED)){
                 bidProjectClient.updateProjectStatus(projectId,IN_PROGRESS.toString());
                 return BID+APPROVED;
             }
             else {
                 return  UPDATION_FAILED;
             }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String executeBidUpdateQueries(Integer bidId, Integer projectId) {
        try {
            Integer bidApproveStatus = jdbcTemplate.update(dbQueries.getApproveBid(), APPROVED.toString(), bidId);
            jdbcTemplate.update(dbQueries.getRejectBid(), REJECTED.toString(), projectId, bidId);
            if(bidApproveStatus==1)
            {
                return BID+APPROVED;
            }
            else {
                return  UPDATION_FAILED;
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bid> getBids(Integer bidId, Integer projectId, Integer freelancerId,String status) {
        try {
            List<Bid> bids;
            if(!freelancerId.equals(0) && !status.isEmpty())
            {
                if(status.equals(PENDING.toString())){
                    return jdbcTemplate.query(dbQueries.getBidsByFreelancerIdAndStatus(),
                            BeanPropertyRowMapper.newInstance(Bid.class), freelancerId,PENDING.toString());
                } else if (status.equals(APPROVED.toString())) {
                   return jdbcTemplate.query(dbQueries.getBidsByFreelancerIdAndStatus(),
                            BeanPropertyRowMapper.newInstance(Bid.class), freelancerId,APPROVED.toString());
                }  else {
                    return jdbcTemplate.query(dbQueries.getBidsByFreelancerIdAndStatus(),
                            BeanPropertyRowMapper.newInstance(Bid.class), freelancerId,REJECTED.toString());
                }
            }
            else if(!projectId.equals(0) && !freelancerId.equals(0))
            {
                return jdbcTemplate.query(dbQueries.getBidByFreelancerIdAndProjectId(),
                        BeanPropertyRowMapper.newInstance(Bid.class),projectId,freelancerId);
            }
            if (!bidId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getBidByBidId(),
                        BeanPropertyRowMapper.newInstance(Bid.class), bidId);
            } else if (!projectId.equals(0)) {
                bids= jdbcTemplate.query(dbQueries.getBidByProjectId(), BeanPropertyRowMapper.newInstance(Bid.class), projectId);
                bids.forEach(bid -> {
                    Integer bidFreelancerId=bid.getFreelancerId();
                    FlResponse<List<UserResponse>> freelancerDetailsResponse= bidUserClient.getFreelancerById(bidFreelancerId);
                    UserResponse freelancerDetails = freelancerDetailsResponse.getResponse().get(0);
                    bid.setFreelancerDetails(freelancerDetails);
                });
                return bids;
            } else {
                return jdbcTemplate.query(dbQueries.getAllBid(), BeanPropertyRowMapper.newInstance(Bid.class));
            }
        } catch (Exception e) {
            throw e;
        }
    }
}




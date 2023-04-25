package com.fl.bid.service;
import com.fl.bid.config.Constant;
import com.fl.bid.model.request.BidRequest;
import com.fl.bid.model.response.Bid;
import com.fl.bid.repository.DbQueries;
import com.fl.bid.service.serviceInterface.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final DbQueries dbQueries;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String insertBid(BidRequest bidRequest) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddBid(),
                    bidRequest.getProjectId(), bidRequest.getFreelancerId(), bidRequest.getAmount(), bidRequest.getDescription());
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
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateBid(), bidRequest.getAmount(), bidRequest.getDescription(), bidId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.UPDATION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Bid> getBids(Integer bidId, Integer projectId, Integer freelancerId) {

        try {
            if (!bidId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getBidByBidId(),
                        BeanPropertyRowMapper.newInstance(Bid.class), projectId);
            }else if(!projectId.equals(0)){
                return jdbcTemplate.query(dbQueries.getBidByProjectId(), BeanPropertyRowMapper.newInstance(Bid.class), bidId);
            }else if(!freelancerId.equals(0)){
                return jdbcTemplate.query(dbQueries.getBidByFreelancerId(), BeanPropertyRowMapper.newInstance(Bid.class), bidId);
            }
            else {
                return jdbcTemplate.query(dbQueries.getAllBid(), BeanPropertyRowMapper.newInstance(Bid.class));
            }
        } catch (Exception e) {
            throw e;
        }
    }
}




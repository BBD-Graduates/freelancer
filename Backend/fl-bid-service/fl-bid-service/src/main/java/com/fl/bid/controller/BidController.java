package com.fl.bid.controller;
import com.fl.bid.model.FlResponse;
import com.fl.bid.model.request.BidRequest;
import com.fl.bid.model.response.Bid;
import com.fl.bid.service.serviceInterface.BidService;
import com.fl.bid.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.fl.bid.config.Constant.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addBid(@Valid @RequestBody BidRequest bidRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, bidService.insertBid(bidRequest), String.format("%s " + INSERTED_SUCCESSFULLY, BID));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + INSERTION_FAILED, BID));
        }
    }
    @DeleteMapping
    public ResponseEntity<FlResponse<String>> deleteBid(@PathVariable("bidId") int bidId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, bidService.deleteBid(bidId), String.format("%s" + DELETED_SUCCESSFULLY, BID));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + DELETION_FAILED, BID));
        }
    }
    @PutMapping
    public ResponseEntity<FlResponse<String>> updateBid(@PathVariable("bidId") Integer bidId, @Valid @RequestBody BidRequest bidRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, bidService.updateBid(bidRequest, bidId), String.format("%s" + UPDATED_SUCCESSFULLY, BID));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + UPDATION_FAILED, BID));
        }
    }
    @GetMapping
    public ResponseEntity<FlResponse<List<Bid>>> getBids(
            @RequestParam(defaultValue = "0", required = false, name = "bidId") Integer bidId,
            @RequestParam(defaultValue = "0", required = false, name = "projectId") Integer projectId,
            @RequestParam(defaultValue = "0", required = false, name = "freelancerId") Integer freelancerId){
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, bidService.getBids(bidId,projectId,freelancerId),String.format("%s" + FETCHED_SUCCESSFULLY, BID));
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, String.format("%s " + NO_RECORD_FOUND));
        }
    }
}




package com.fl.bid.model.response;
import com.fl.bid.model.response.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidResponse {
    private int bidId;
    @Builder.Default
    List<Bid> bids =new ArrayList<>();

}

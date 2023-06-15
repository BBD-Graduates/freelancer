package com.fl.user.service.serviceInterface;

import com.fl.user.model.request.PortfolioRequest;
import com.fl.user.model.response.PortfolioResponse;

import java.util.List;

public interface PortfolioService {
    String addPortfolio(PortfolioRequest portfolioRequest);

    List<PortfolioResponse> getPortfolio( Integer userId);

    String deletePortfolio(Integer portfolioId);
}

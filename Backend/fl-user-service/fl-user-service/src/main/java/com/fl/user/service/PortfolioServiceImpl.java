package com.fl.user.service;

import com.fl.user.config.Constant;
import com.fl.user.model.request.PortfolioRequest;
import com.fl.user.model.response.PortfolioResponse;
import com.fl.user.repository.DbQueries;
import com.fl.user.service.serviceInterface.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String addPortfolio(PortfolioRequest portfolioRequest) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddPortfolio(),portfolioRequest.getUserId(),portfolioRequest.getTitle()
                    , portfolioRequest.getDescription(),portfolioRequest.getImageURL());
            if (insertStatus > 0) {
                return Constant.INSERTION_SUCCESSFUL;
            } else {
                return Constant.INSERTION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<PortfolioResponse> getPortfolio(Integer userId) {
        try {
            return jdbcTemplate.query(dbQueries.getUserPortfolio(), BeanPropertyRowMapper.newInstance(PortfolioResponse.class),userId);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String deletePortfolio(Integer portfolioId) {
        try {
            int deleteStatus = jdbcTemplate.update(dbQueries.getDeletePortfolio(),portfolioId);
            if (deleteStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.DELETION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

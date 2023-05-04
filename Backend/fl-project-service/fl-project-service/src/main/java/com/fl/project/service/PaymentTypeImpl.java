package com.fl.project.service;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fl.project.model.request.PaymentTypeRequest;
import com.fl.project.model.response.PaymentTypeResponse;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.PaymentTypeService;

import lombok.RequiredArgsConstructor;
import static com.fl.project.config.Constant.*;

@Service
@RequiredArgsConstructor
public class PaymentTypeImpl implements PaymentTypeService {
    private final JdbcTemplate jdbcTemplate;

    private final DbQueries dbQueries;

    @Override
    public String savePaymentType(PaymentTypeRequest project) {
        try {
            int i = jdbcTemplate.update(dbQueries.getAddPaymentType(), project.getPaymentType());
            if (i > 0)
                return INSERTED_SUCCESSFULLY;
            else
                return CANT_PROCESS_REQUEST;
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public List<PaymentTypeResponse> getAllPaymentType() {
        try {
            return jdbcTemplate.query(dbQueries.getSelectAllPaymentType(),
                    BeanPropertyRowMapper.newInstance(PaymentTypeResponse.class));
        } catch (Exception ex) {
            throw ex;
        }
    }

}

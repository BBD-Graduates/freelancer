package com.fl.project.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fl.project.model.request.RatingRequest;
import com.fl.project.model.response.RatingResponse;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.RatingService;

import lombok.RequiredArgsConstructor;
import static com.fl.project.config.Constant.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingImpl implements RatingService {
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String insertRating(RatingRequest rating) {
        try {
            int inserted = jdbcTemplate.update(dbQueries.getAddRating(), rating.getUserId(), rating.getProjectId(),
                    rating.getRatingDescription(), rating.getRating());
            if (inserted > 0) {
                return INSERTED_SUCCESSFULLY;
            } else {
                return CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<RatingResponse> getRatings(Integer ratingId, Integer userId, Integer ProjectId) {
        try {
            if (!ratingId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getRatingByRatingId(),
                        BeanPropertyRowMapper.newInstance(RatingResponse.class), ratingId);
            } else if (!userId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getRatingByUserId(),
                        BeanPropertyRowMapper.newInstance(RatingResponse.class), userId);
            } else if (!ProjectId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getRatingByProjectId(),
                        BeanPropertyRowMapper.newInstance(RatingResponse.class), ProjectId);
            } else {
                return jdbcTemplate.query(dbQueries.getAllRating(),
                        BeanPropertyRowMapper.newInstance(RatingResponse.class));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateRating(Integer ratingId, RatingRequest rating) {
        try {
            int updated = jdbcTemplate.update(dbQueries.getUpdateRatingByRatingId(), rating.getRatingDescription(),
                    rating.getRating(), ratingId);
            if (updated > 0) {
                return UPDATED_SUCCESSFULLY;
            } else {
                return CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String deleteRating(Integer ratingId) {
        int deleted = jdbcTemplate.update(dbQueries.getRemoveRatingByRatingId(), ratingId);
        if (1 == deleted) {
            return DELETED_SUCCESSFULLY;
        } else {
            return CANT_PROCESS_REQUEST;
        }
    }
}

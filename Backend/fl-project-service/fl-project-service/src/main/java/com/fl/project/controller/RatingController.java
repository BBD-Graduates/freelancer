package com.fl.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fl.project.model.FlResponse;
import com.fl.project.model.request.RatingRequest;
import com.fl.project.model.response.RatingResponse;
import com.fl.project.service.serviceInterface.RatingService;
import com.fl.project.util.FlResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static com.fl.project.config.Constant.*;

import java.util.List;

@RestController
@RequestMapping("/projects/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> createRating(@Valid @RequestBody RatingRequest rating) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, ratingService.insertRating(rating),
                    RATING + INSERTED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + INSERTION_FAILED, RATING));
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<RatingResponse>>> getRatings(
            @RequestParam(defaultValue = "0", required = false) Integer ratingId,
            @RequestParam(defaultValue = "0", required = false) Integer userId,
            @RequestParam(defaultValue = "0", required = false) Integer ProjectId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK,
                    ratingService.getRatings(ratingId, userId, ProjectId), RATING + FETCHED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + CANT_PROCESS_REQUEST, RATING));
        }
    }

    @PutMapping
    public ResponseEntity<FlResponse<String>> updateRaing(@RequestParam("ratingId") Integer ratingId,
            @RequestBody() RatingRequest ratingReq) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, ratingService.updateRating(ratingId, ratingReq),
                    RATING + UPDATED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + UPDATION_FAILED, RATING));
        }
    }

    @DeleteMapping
    public ResponseEntity<FlResponse<String>> removeRating(@PathVariable("ratingId") Integer ratingId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, ratingService.deleteRating(ratingId),
                    RATING + DELETED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,
                    String.format("%s " + DELETION_FAILED, RATING));
        }
    }
}

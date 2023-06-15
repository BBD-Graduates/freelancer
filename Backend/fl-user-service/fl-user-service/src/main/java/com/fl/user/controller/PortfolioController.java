package com.fl.user.controller;

import com.fl.user.model.FlResponse;
import com.fl.user.model.request.PortfolioRequest;
import com.fl.user.model.response.PortfolioResponse;
import com.fl.user.service.serviceInterface.PortfolioService;
import com.fl.user.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.user.config.Constant.*;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addPortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, portfolioService.addPortfolio(portfolioRequest),PORTFOLIO+INSERTION_SUCCESSFUL);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, PORTFOLIO+INSERTION_FAILED);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FlResponse<List<PortfolioResponse>>> getPortfolio(@PathVariable("userId") Integer userId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, portfolioService.getPortfolio(userId),PORTFOLIO+FETCHED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, CANT_PROCESS_REQUEST);
        }
    }

   @DeleteMapping("/{portfolioId}")
    public ResponseEntity<FlResponse<String>> deletePortfolio(@PathVariable("portfolioId") Integer portfolioId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, portfolioService.deletePortfolio(portfolioId),PORTFOLIO+DELETED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, PORTFOLIO+DELETION_FAILED);
        }
    }
}

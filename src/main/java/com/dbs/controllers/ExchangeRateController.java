package com.dbs.controllers;

import com.dbs.controllers.requests.ExchangeRateRequest;
import com.dbs.entities.ExchangeRate;
import com.dbs.services.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeRateController {

    @Autowired
    private ExchangeService exchangeService;

    @Operation(summary = "Get all exchange rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all exchangeRates"),
            @ApiResponse(responseCode = "404", description = "Not found - ExchangeRates were not found")
    })
    @PostMapping
    public ResponseEntity<List<ExchangeRate>> getExchangeRates() {
        return ResponseEntity.ok(exchangeService.getAllExchangeRates());
    }

    @Operation(summary = "Get specific exchange rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved exchangeRate"),
            @ApiResponse(responseCode = "404", description = "Not found - ExchangeRate was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRate> getExchangeRate(@PathVariable int id) {
        return ResponseEntity.ok(exchangeService.getExchangeRate(id));
    }

//    @Operation(summary = "Create exchange rate")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully created exchange rate")
//    })
//    @PostMapping
//    public ResponseEntity<ExchangeRate> createExchangeRate(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) {
//        return ResponseEntity.ok(exchangeService.createExchangeRate(exchangeRateRequestToExchangeRate(exchangeRateRequest)));
//    }

    @Operation(summary = "Update exchange rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated exchange rate")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest, @PathVariable int id) {
        return ResponseEntity.ok(exchangeService.updateExchangeRate(exchangeRateRequestToExchangeRate(exchangeRateRequest), id));
    }

    @Operation(summary = "Delete exchange rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted exchange rate")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExchangeRate(@PathVariable int id) {
        exchangeService.deleteExchangeRate(id);
        return ResponseEntity.ok("success");
    }

    private ExchangeRate exchangeRateRequestToExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        return ExchangeRate.builder()
                .baseCurrency(exchangeRateRequest.getBaseCurrency())
                .exchangeCurrency(exchangeRateRequest.getExchangeCurrency())
                .rate(exchangeRateRequest.getRate())
                .build();
    }


}

package com.dbs.controllers;

import com.dbs.controllers.requests.CurrencyRequest;
import com.dbs.controllers.requests.WalletRequest;
import com.dbs.entities.Currency;
import com.dbs.entities.Wallet;
import com.dbs.services.CurrencyService;
import com.dbs.services.CurrencyService;
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
@RequestMapping("/api/v1/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @Operation(summary = "Get currency for specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wallet"),
            @ApiResponse(responseCode = "404", description = "Not found - Wallet was not found")
    })
    @GetMapping("/retrieve")
    public ResponseEntity<List<Currency>> getCurrencyForWallet(@RequestBody CurrencyRequest currencyRequest) {
        return ResponseEntity.ok(currencyService.getAllCurrenciesForUser(currencyRequest.getWalletId()));
    }

    @Operation(summary = "Get all currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all currencys"),
            @ApiResponse(responseCode = "404", description = "Not found - Currencys were not found")
    })
    @GetMapping
    public ResponseEntity<List<Currency>> getCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @Operation(summary = "Get specific currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved currency"),
            @ApiResponse(responseCode = "404", description = "Not found - Currency was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable int id) {
        return ResponseEntity.ok(currencyService.getCurrency(id));
    }

    @Operation(summary = "Create currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created currency")
    })
    @PostMapping
    public ResponseEntity<Currency> createCurrency(@Valid @RequestBody CurrencyRequest currencyRequest) {
        return ResponseEntity.ok(currencyService.createCurrency(currencyRequestToCurrency(currencyRequest)));
    }

    @Operation(summary = "Update currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated currency")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Currency> updateCurrency(@Valid @RequestBody CurrencyRequest currencyRequest, @PathVariable int id) {
        return ResponseEntity.ok(currencyService.updateCurrency(currencyRequestToCurrency(currencyRequest), id));
    }

    @Operation(summary = "Delete currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted currency")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCurrency(@PathVariable int id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.ok("success");
    }

    private Currency currencyRequestToCurrency(CurrencyRequest currencyRequest) {
        return Currency.builder()
                .currency(currencyRequest.getCurrency())
                .amount(currencyRequest.getAmount())
                .walletId(currencyRequest.getWalletId())
                .build();
    }


}

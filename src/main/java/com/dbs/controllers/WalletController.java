package com.dbs.controllers;

import com.dbs.controllers.requests.WalletRequest;
import com.dbs.entities.Wallet;
import com.dbs.services.UserService;
import com.dbs.services.WalletService;
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
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get wallet for specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wallet"),
            @ApiResponse(responseCode = "404", description = "Not found - Wallet was not found")
    })
    @GetMapping("/retrieve")
    public ResponseEntity<Wallet> getWallet(@RequestBody WalletRequest walletRequest) {
        return ResponseEntity.ok(walletService.getWalletByUser(walletRequest.getUserId()));
    }

    @Operation(summary = "Get all wallet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all wallets"),
            @ApiResponse(responseCode = "404", description = "Not found - Wallets were not found")
    })
    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @Operation(summary = "Get specific wallet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wallet"),
            @ApiResponse(responseCode = "404", description = "Not found - Wallet was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWallet(@PathVariable int id) {
        return ResponseEntity.ok(walletService.getWallet(id));
    }

    @Operation(summary = "Create wallet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created wallet ")
    })
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody WalletRequest walletRequest) {
        return ResponseEntity.ok(walletService.createWallet(walletRequest));
    }

    @Operation(summary = "Update wallet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated wallet ")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@Valid @RequestBody WalletRequest walletRequest, @PathVariable int id) {
        return ResponseEntity.ok(walletService.updateWallet(walletRequestToWallet(walletRequest), id));
    }

    @Operation(summary = "Delete wallet ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted wallet ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable int id) {
        walletService.deleteWallet(id);
        return ResponseEntity.ok("success");
    }

    private Wallet walletRequestToWallet(WalletRequest walletRequest) {
        return Wallet.builder()
                .name(walletRequest.getName())
                .build();
    }


}

package com.dbs.controllers;

import com.dbs.controllers.requests.TransactionRequest;
import com.dbs.entities.Transaction;
import com.dbs.services.TransactionService;
import com.dbs.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get transaction for specific wallet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction"),
            @ApiResponse(responseCode = "404", description = "Not found - Transaction was not found")
    })
    @GetMapping("/retrieve")
    public ResponseEntity<List<Transaction>> getTransaction(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.getTransactionByWalletId(transactionRequest.getWalletId()));
    }

    @Operation(summary = "Get all transaction ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all transactions"),
            @ApiResponse(responseCode = "404", description = "Not found - Transactions were not found")
    })
    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @Operation(summary = "Get specific transaction ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction"),
            @ApiResponse(responseCode = "404", description = "Not found - Transaction was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.getTransaction(id));
    }

    @Operation(summary = "Create transaction ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created transaction ")
    })
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequest));
    }

//    @Operation(summary = "Update transaction ")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully updated transaction ")
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody TransactionRequest transactionRequest, @PathVariable int id) {
//        return ResponseEntity.ok(transactionService.updateTransaction(transactionRequestToTransaction(transactionRequest), id));
//    }

    @Operation(summary = "Delete transaction ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted transaction ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("success");
    }


}

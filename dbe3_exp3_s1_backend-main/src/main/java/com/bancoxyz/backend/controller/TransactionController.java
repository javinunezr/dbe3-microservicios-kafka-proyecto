package com.bancoxyz.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoxyz.backend.model.dto.TransactionResponse;
import com.bancoxyz.backend.service.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("backend/api")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
   }

  @GetMapping("/transaction")
  public ResponseEntity<List<TransactionResponse>> getAllTransactions(){
    return ResponseEntity.ok(transactionService.findAllTransactions());
  }
 
}

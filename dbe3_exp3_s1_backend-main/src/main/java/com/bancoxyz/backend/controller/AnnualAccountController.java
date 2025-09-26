package com.bancoxyz.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoxyz.backend.model.dto.AnnualAccountResponse;
import com.bancoxyz.backend.service.AnnualAccountService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("backend/api")
public class AnnualAccountController {

  @Autowired
  private AnnualAccountService annualAccountService;

  public AnnualAccountController(AnnualAccountService annualAccountService) {
    this.annualAccountService = annualAccountService;
  }

  @GetMapping("/annual-account")
  public ResponseEntity<List<AnnualAccountResponse>> getAllAnnualAccounts(){
    return ResponseEntity.ok(annualAccountService.findAllAnnualAccounts());
  }
    
}

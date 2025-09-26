package com.bancoxyz.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoxyz.backend.model.dto.InterestResponse;
import com.bancoxyz.backend.service.InterestService;

@RestController
@RequestMapping("backend/api")
public class InterestController {

  @Autowired
  private InterestService interestService;

  public InterestController(InterestService interestService) {
    this.interestService = interestService;
   }

  @GetMapping("/interest")
  public ResponseEntity<List<InterestResponse>> getAllTransactions(){
    return ResponseEntity.ok(interestService.findAllInterests());
  }
}

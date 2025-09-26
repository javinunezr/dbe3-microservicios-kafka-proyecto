package com.bancoxyz.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoxyz.backend.model.dto.InterestResponse;
import com.bancoxyz.backend.repository.InterestRepository;

@Service
public class InterestService {

  @Autowired
  private InterestRepository interestRepository;

  public InterestService(InterestRepository interestRepository) {
    this.interestRepository = interestRepository;
  }

  public List<InterestResponse> findAllInterests() {
     return interestRepository.findAll().stream() 
      .map(interest -> new InterestResponse(
          interest.getAccountId(),
          interest.getClientName(),
          interest.getBalance(),
          interest.getClientAge(),
          interest.getInterestType()
      ))
      .toList();
      
  }
  
}

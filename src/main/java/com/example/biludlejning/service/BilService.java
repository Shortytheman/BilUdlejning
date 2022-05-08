package com.example.biludlejning.service;

import com.example.biludlejning.repository.BilRepository;
import org.springframework.stereotype.Service;

@Service
public class BilService {

  BilRepository bilRepository;

  // Her skal vi kalde metoder fra bilrepository, så vores controller ser lidt mere clean ud.

  // Constructor dependency injection
  public BilService(BilRepository bilRepository){
    this.bilRepository = bilRepository;
  }





}

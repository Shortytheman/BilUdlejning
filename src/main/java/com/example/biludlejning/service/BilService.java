package com.example.biludlejning.service;

import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bildata;
import com.example.biludlejning.repository.BilRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class BilService {

  BilRepository bilRepository;

  // Her skal vi kalde metoder fra bilrepository, så vores controller ser lidt mere clean ud.

  // Constructor dependency injection
  public BilService(BilRepository bilRepository){
    this.bilRepository = bilRepository;
  }
  public ArrayList<Bil> seBiler() {
    return bilRepository.seBiler();
  }
  public LinkedHashMap<Bil, Bildata> modelAntal() {
    return bilRepository.modelAntal();
  }
  public ArrayList<Integer> antalBilAfModel() {
    return new ArrayList<>();
  }
  public ArrayList<Bil> enAfHverModel() {
    return bilRepository.enAfHverModel();
  }





}

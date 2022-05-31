package com.example.biludlejning.service;

import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bildata;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.BilRepository;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

//Skrevet af Johannes og Niklas

@Service
public class BilService {

  BilRepository bilRepository;
  LejeAftaleRepository lejeAftaleRepository;

  // Her skal vi kalde metoder fra bilrepository, så vores controller ser lidt mere clean ud.

  // Constructor dependency injection
  public BilService(BilRepository bilRepository, LejeAftaleRepository lejeAftaleRepository){
    this.bilRepository = bilRepository;
    this.lejeAftaleRepository = lejeAftaleRepository;
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
  public LinkedHashMap<String, Double> resterendeBetalingPerModel() {
    return bilRepository.resterendeBetalingPerModel();
  }
  public void tilføjBil(Bil bil){
    bilRepository.tilføjBil(bil);
  }
  public ArrayList<LejeAftale> slutAftaleAdvarsel(){
    return lejeAftaleRepository.slutAftaleAdvarsel();
  }
  public Bil findBilMedVognnummer(int vognnummer) {
    return bilRepository.findBilMedVognnummer(vognnummer);
  }
  public void sletBil(int vognnummer) {
    bilRepository.sletBil(vognnummer);
  }
}
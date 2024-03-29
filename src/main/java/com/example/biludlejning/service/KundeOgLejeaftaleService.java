package com.example.biludlejning.service;

import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Kunde;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.BilRepository;
import com.example.biludlejning.repository.KundeRepository;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//Skrevet af alle i gruppen

@Service
public class KundeOgLejeaftaleService {

  KundeRepository kundeRepository;
  LejeAftaleRepository lejeAftaleRepository;
  BilRepository bilRepository;

  public KundeOgLejeaftaleService(KundeRepository kundeRepository, LejeAftaleRepository lejeAftaleRepository, BilRepository bilRepository) {
    this.kundeRepository = kundeRepository;
    this.lejeAftaleRepository = lejeAftaleRepository;
    this.bilRepository = bilRepository;
  }

  //Lejeaftale metoder

  public String opretLejekontrakt(LejeAftale lejeAftale) {
    return lejeAftaleRepository.opretLejekontrakt(lejeAftale);
  }

  public void opretLejeaftale(LejeAftale lejeAftale){
    lejeAftaleRepository.opretLejeaftale(lejeAftale);
  }

  //Kunde metoder

  public void opretKunde(Kunde kunde){
    kundeRepository.opretKunde(kunde);
  }
  public ArrayList<Kunde> visAlleKunder() {
    return kundeRepository.visAlleKunder();
  }
  public ArrayList<Bil> seIkkeUdlejedeBiler() {
    return bilRepository.seIkkeUdlejedeBiler();
  }

  public void sletKunde(int kundeId){
    kundeRepository.sletKundeMedId(kundeId);
  }

  public void opdaterKunde(Kunde kunde){
    kundeRepository.opdaterKunde(kunde);
  }

  public Kunde findKundeMedId(int Id){
  return kundeRepository.findKundeMedId(Id);
  }

}

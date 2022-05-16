package com.example.biludlejning.service;

import com.example.biludlejning.model.Kunde;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.KundeRepository;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;

@Service
public class KundeOgLejeaftaleService {

  KundeRepository kundeRepository;
  LejeAftaleRepository lejeAftaleRepository;


  //Lejeaftale metoder
  public KundeOgLejeaftaleService(KundeRepository kundeRepository, LejeAftaleRepository lejeAftaleRepository) {
    this.kundeRepository = kundeRepository;
    this.lejeAftaleRepository = lejeAftaleRepository;
  }

  public String lavLejeKontrakt(LejeAftale lejeAftale) {
    return lejeAftaleRepository.lavLejeKontrakt(lejeAftale);
  }

  public void lavLejeaftale(LejeAftale lejeAftale){
    lejeAftaleRepository.tilføjLejeAftale(lejeAftale);
  }

  public LejeAftale lejeAftaleEfterKundeId(int kundeId){
    return lejeAftaleRepository.findlejeAftaleEfterKundeId(kundeId);
  }

  //Kunde metoder

  public void opretKunde(Kunde kunde){
    kundeRepository.opretKunde(kunde);
  }

}

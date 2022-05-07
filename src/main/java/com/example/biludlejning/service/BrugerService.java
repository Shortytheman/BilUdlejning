package com.example.biludlejning.service;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.repository.BrugerRepository;

public class BrugerService {

  BrugerRepository brugerRepository;

  public BrugerService(BrugerRepository brugerRepository){
    this.brugerRepository = brugerRepository;
  }

  public Bruger findBruger(String brugernavn){
    return brugerRepository.findBruger(brugernavn);
  }

  public boolean korrektLogin(String brugernavn, String kodeord, Bruger bruger){
    boolean kanLoggeInd = false;

    if (brugernavn.length() > 0 && kodeord.length() > 0 &&
        kodeord.equalsIgnoreCase(bruger.getKodeord())){
      kanLoggeInd = true;
    }
    return kanLoggeInd;
  }

}

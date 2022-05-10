package com.example.biludlejning.service;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.repository.BrugerRepository;
import org.springframework.stereotype.Service;

@Service
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
    if (bruger != null) {
      if (brugernavn.length() > 0 && kodeord.length() > 0 &&
          kodeord.equalsIgnoreCase(bruger.getKodeord())) {
        kanLoggeInd = true;
      }
    }
    return kanLoggeInd;
  }

  public String loginFejl(Bruger bruger, String kodeord){
    String fejlmeddelse = "";
    if (bruger != null) {
      if (bruger.getBrugernavn().length() < 1 || bruger.getKodeord().length() < 1) {
        fejlmeddelse = "Brugernavn eller kodeord er for kort.";
      } else if (!findBruger(bruger.getBrugernavn()).getKodeord().equalsIgnoreCase(kodeord)) {
        fejlmeddelse = "Fejl i adgangskoden";
      }
    } else fejlmeddelse = "brugernavnet tilhører ikke en oprettet bruger";
    return fejlmeddelse;
  }

  public void opretBruger(Bruger bruger){
    brugerRepository.opretBruger(bruger);
  }

}

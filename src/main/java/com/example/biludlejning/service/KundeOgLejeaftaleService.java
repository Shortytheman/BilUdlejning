package com.example.biludlejning.service;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.KundeRepository;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.springframework.stereotype.Service;

@Service
public class KundeOgLejeaftaleService {

  KundeRepository kundeRepository;
  LejeAftaleRepository lejeAftaleRepository;

  public KundeOgLejeaftaleService(KundeRepository kundeRepository, LejeAftaleRepository lejeAftaleRepository) {
    this.kundeRepository = kundeRepository;
    this.lejeAftaleRepository = lejeAftaleRepository;
  }

  public String lavLejeKontrakt(LejeAftale lejeAftale) {
    return lejeAftaleRepository.lavLejeKontrakt(lejeAftale);
  }

}

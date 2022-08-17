package com.example.biludlejning.service;

import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.repository.SkadesrapportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//Skrevet af alle i gruppen

@Service
public class SkadeService {

  SkadesrapportRepository skadesrapportRepository;

  public SkadeService(SkadesrapportRepository skadesrapportRepository){
    this.skadesrapportRepository = skadesrapportRepository;
  }

  public ArrayList<Skadesrapport> seSkadesrapporter(){
    return skadesrapportRepository.seSkadesrapporter();
  }

  public Skadesrapport findSkadesrapport(int skadesrapportId){
    return skadesrapportRepository.findSkadesrapport(skadesrapportId);
  }

  public Skadesrapport findSkadesrapportMedVognnummer(int vognnummer){
    return skadesrapportRepository.findSkadesrapportMedVognnummer(vognnummer);
  }

  public void opretSkadesrapport(Skadesrapport skadesrapport){
    skadesrapportRepository.opretSkadesrapport(skadesrapport);
  }

  public double findTotalSkadePris(int skadesrapportId){
    return skadesrapportRepository.totalSkadePris(skadesrapportId);
  }

}

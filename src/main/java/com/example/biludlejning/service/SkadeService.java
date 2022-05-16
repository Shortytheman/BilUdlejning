package com.example.biludlejning.service;

import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.repository.SkadesrapportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

}

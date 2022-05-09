package com.example.biludlejning.repository;


import com.example.biludlejning.model.Bil;
import com.example.biludlejning.utilities.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;

@Repository
public class BilRepository {

  Connection connection;

  public BilRepository(){
    connection = ConnectionManager.connectToSql();
  }

  public ArrayList<Bil> visAlleBiler(){
    ArrayList<Bil> biler = new ArrayList<>();
    // Her skal metoden til at vise alle biler være.
    return biler;
  }

  public Bil findBilmedStelnummer(int stelnummer){
    Bil bil = new Bil();
    //DB kald som returnerer bil med bestemt stelnummer og returnerer den.
    return bil;
  }

  public void sletBil(int stelnummer){
    // DB kald som sletter bil fra DB.
  }

  public void opdaterBil(Bil bil){
    // DB kald som opdaterer bil i DB. evt. fra udlejet = false, til udlejet = true.
  }

}

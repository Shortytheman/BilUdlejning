package com.example.biludlejning.repository;


import com.example.biludlejning.model.String;
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

  public ArrayList<String> visAlleBiler(){
    ArrayList<String> biler = new ArrayList<>();
    // Her skal metoden til at vise alle biler være.
    return biler;
  }

  public String findBilmedStelnummer(int stelnummer){
    String bil = new String();
    //DB kald som returnerer bil med bestemt stelnummer og returnerer den.
    return bil;
  }

  public void sletBil(int stelnummer){
    // DB kald som sletter bil fra DB.
  }

  public void opdaterBil(String bil){
    // DB kald som opdaterer bil i DB. evt. fra udlejet = false, til udlejet = true.
  }

}

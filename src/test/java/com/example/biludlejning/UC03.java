package com.example.biludlejning;

import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.repository.SkadesrapportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class UC03 {

  @Autowired
  //Arrange
  SkadesrapportRepository srr = new SkadesrapportRepository();

  @Test
  void findSkadesRapportMedVognnummerTest(){
    //Arrange
    Skadesrapport sr = new Skadesrapport("mogens","mogens@gmail.com",313,313);
    srr.opretSkadesrapport(sr);
    SkadesrapportRepository skadesrep = new SkadesrapportRepository();
    //Act and Assert
    assertThat(skadesrep.findSkadesrapportMedVognnummer(313).getKundeId(), is(313));
  }

}

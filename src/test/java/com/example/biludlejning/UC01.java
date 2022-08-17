package com.example.biludlejning;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.example.biludlejning.service.KundeOgLejeaftaleService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class UC01 {

  @Autowired
  //Arrange
  private KundeOgLejeaftaleService kl;
  private LejeAftaleRepository lr = new LejeAftaleRepository();

  @Test
  void opretLejeaftaleTilDb(){
    //Act
    //Vi opretter en lejeaftale i databasen med de givne oplysninger
    LejeAftale ff = new LejeAftale(1,777,100,100,"010123");
    kl.opretLejeaftale(ff);
    //Assert
    //Vi asserter at lejeaftalen som vi finder ud fra kundeId har et specifikt vognnummer
    assertThat(lr.findlejeAftaleEfterKundeId(1).getVognnummer(), is(777));
    //Passed!
  }

  //Arrange - act - assert

}

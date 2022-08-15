package com.example.biludlejning;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.LejeAftaleRepository;
import com.example.biludlejning.repository.SkadesrapportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.service.KundeOgLejeaftaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    kl.lavLejeaftale(ff);
    //Assert
    //Vi asserter at lejeaftalen som vi finder ud fra kundeId har et specifikt vognnummer
    assertThat(lr.findlejeAftaleEfterKundeId(1).getVognnummer(), is(777));
    //Passed!
  }

  //Arrange - act - assert

}

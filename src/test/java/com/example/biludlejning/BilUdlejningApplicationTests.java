package com.example.biludlejning;
import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Bildata;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.repository.BilRepository;
import com.example.biludlejning.repository.LejeAftaleRepository;
import com.example.biludlejning.repository.SkadesrapportRepository;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Skrevet af Johannes og Niklas og reviewet af hele gruppen

@SpringBootTest
class BilUdlejningApplicationTests {

  @Test
  void contextLoads() {
  }
  @Test
  void slutAftaleAdvarselTest() {
    //Tester om metoden virker ved årsskifte
    boolean årsskifteAdvarsel = slutAftaleAdvarselTest("301222", "020123");
    assertTrue(årsskifteAdvarsel);

    //Tester om metoden virker ved månedsskifte
    boolean månedsskifteAdvarsel = slutAftaleAdvarselTest("290622", "050722");
    assertTrue(månedsskifteAdvarsel);
  }
  public boolean slutAftaleAdvarselTest(String dagsdato, String dato) {
    ArrayList<LejeAftale> udløberSnart = null;
    String dagsDatoString;
    DateTimeFormatter datoenIdag = DateTimeFormatter.ofPattern("ddMMyy");
    LocalDateTime nu = LocalDateTime.now();
    dagsDatoString = datoenIdag.format(nu);
    int dagsDatoDag = Integer.parseInt(dagsdato.substring(0, 2));
    int dagsDatoMåned = Integer.parseInt(dagsdato.substring(2, 4));
    int dagsDatoÅr = Integer.parseInt(dagsdato.substring(4, 6));
    boolean advarsel = false;
    boolean årMatcher = false;
    boolean månedMatcher = false;
    int slutDatoDag = Integer.parseInt(dato.substring(0,2));
    int slutDatoMåned = Integer.parseInt(dato.substring(2,4));
    int slutDatoÅr = Integer.parseInt(dato.substring(4,6));
    if (slutDatoÅr == dagsDatoÅr) {
      årMatcher = true;
      if (slutDatoMåned == dagsDatoMåned) {
        månedMatcher = true;
      }
    }
    if (årMatcher && månedMatcher) {
      if (dagsDatoDag < 6) {
        advarsel = true;
      } else if (slutDatoDag - 6 < dagsDatoDag) {
        advarsel = true;
      }
    }
    if (slutDatoÅr - 1 == dagsDatoÅr && slutDatoDag < 6 && dagsDatoMåned == 12 && dagsDatoDag > 24) {
      advarsel = true;
    }
    if (årMatcher && !månedMatcher && dagsDatoMåned == slutDatoMåned - 1 && slutDatoDag < 6 && dagsDatoDag > 24) {
      advarsel = true;
    }
    return advarsel;
  }

  @Test
  void findSkadesRapportMedVognnummerTest(){
    SkadesrapportRepository skadesrep = new SkadesrapportRepository();
    assertThat(skadesrep.findSkadesrapportMedVognnummer(2).getKundeId(), is(6));
  }

  @Test
  void findlejeAftaleEfterKundeId(){
    LejeAftaleRepository lejeaftalerep = new LejeAftaleRepository();
    assertThat(lejeaftalerep.findlejeAftaleEfterKundeId(32).getVognnummer(),is(567));
  }

}

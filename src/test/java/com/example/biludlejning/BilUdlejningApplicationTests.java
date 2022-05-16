package com.example.biludlejning;

import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.repository.LejeAftaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BilUdlejningApplicationTests {
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
}

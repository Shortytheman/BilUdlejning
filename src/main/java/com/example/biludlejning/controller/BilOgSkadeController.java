package com.example.biludlejning.controller;

import com.example.biludlejning.model.Bil;
import com.example.biludlejning.model.Skadesrapport;
import com.example.biludlejning.service.BilService;
import com.example.biludlejning.service.SkadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;


//Hovedsageligt skrevet af Niklas

@Controller
public class BilOgSkadeController {
  BilService bilService;
  SkadeService skadeService;

  /*Dependency Injection - Controller er afhængig af både Bilservice og Skadeservice og ved DI, slipper controller for
  selv at skulle oprette objekter af disse klasser. */

  public BilOgSkadeController(BilService bilService, SkadeService skadeService){
    this.skadeService = skadeService;
    this.bilService = bilService;
  }

  //Skrevet af Johannes
  @GetMapping("/fudvikler")
  public String fudvikler(Model model) {
    //Vi bruger Model til at sende informationer fra vores Controller til vores view.
    model.addAttribute("modelAntal", bilService.modelAntal());
    model.addAttribute("resterendeBetalingPerModel", bilService.resterendeBetalingPerModel());
    model.addAttribute("slutAftaleAdvarsel", bilService.slutAftaleAdvarsel());
    return "fudvikler";
  }
  //Skrevet
  @GetMapping("/biloverblik")
  public String overblik(Model model) {
    model.addAttribute("biler", bilService.seBiler());
    return "biloverblik";
  }
  //Skrevet af Johannes
  @GetMapping("/sletbil/{vognnummer}")
  public String sletBil(@PathVariable("vognnummer") int vognnummer){
    bilService.sletBil(vognnummer);
    return "redirect:/biloverblik";
  }
  @GetMapping("/seSkadesrapporter")
  public String seSkadesrapporter(Model model){
    model.addAttribute("skadesrapporter",skadeService.seSkadesrapporter());
    return "seSkadesrapporter";
  }

  @GetMapping("/opretSkadesrapport")
  public String opretSkadesrapport(){
    return "opretSkadesrapport";
  }

  @PostMapping("/opretSkadesrapport")
  public String opretSkadesrapporten(@RequestParam(name = "medarbejdernavn") String medarbejdernavn,
                                     @RequestParam(name = "medarbejderemail") String medarbejderemail,
                                     @RequestParam(name = "vognnummer") int vognnummer,
                                     @RequestParam(required = false, name = "skade1") String skade1, @RequestParam(required = false, name = "pris1") Double pris1,
                                     @RequestParam(required = false, name = "skade2") String skade2, @RequestParam(required = false, name = "pris2") Double pris2,
                                     @RequestParam(required = false, name = "skade3") String skade3, @RequestParam(required = false, name = "pris3") Double pris3,
                                     @RequestParam(name = "kundeid") int kundeid, HttpSession httpSession){
    Skadesrapport skadesrapport = new Skadesrapport();
    skadesrapport.setMedarbejderNavn(medarbejdernavn);
    skadesrapport.setMedarbejderEmail(medarbejderemail);
    skadesrapport.setVognnummer(vognnummer);
    LinkedHashMap<String,Double> skader = new LinkedHashMap<>();
    //Vi tager imod 0 til 3 skader ved hver skadesrapport der oprettes, derfor er hver enkelt skade markeret som "Required = false".
    skader.put(skade1,pris1);
    skader.put(skade2,pris2);
    skader.put(skade3,pris3);
    skadesrapport.setSkader(skader);
    skadesrapport.setKundeId(kundeid);
    skadeService.opretSkadesrapport(skadesrapport);
    //Denne sender en meddelse til viewet om hvilket skadesrapport ID der lige er blevet oprettet.
    httpSession.setAttribute("meddelse", "Skadesrapport oprettet med skadesrapportID: " +
        skadeService.findSkadesrapportMedVognnummer(vognnummer).getSkadesrapportId());
    return "redirect:/";
  }

  @GetMapping("/seskadesrapport/{skadesrapportid}")
  public String seskadesrapport(@PathVariable("skadesrapportid") int skadesrapportId, Model model, HttpSession httpSession){
    model.addAttribute("skadesrapport",skadeService.findSkadesrapport(skadesrapportId));
    model.addAttribute("totalskadepris",skadeService.findTotalSkadePris(skadesrapportId));
    //Denne tømmer "meddelse"s attributten så når der ikke LIGE er lavet en rapport, står der intet.
    httpSession.setAttribute("meddelse", "");
    return "seskadesrapport";
  }

  @GetMapping("/tilføjbil")
  public String opretBil(){
    return "tilføjbil";
  }

//Da oprettelse af de individuelle biler med vores opsætning ikke rigtig kunne lade sig gøre med en enkelt metode, oprettede vi en for hver slags bil.
  //<editor-fold desc="Her oprettes alle de individuelle biler - OBS 9 ret ens metoder.">
  @PostMapping("/tilføjpeugeot208")
  public String tilføjPeugeot208(@RequestParam int vognnummer1, @RequestParam int stelnummer1){
    Bil bil = new Bil();
    bil.setModel("208");
    bil.setMærke("Peugeot");
    bil.setStelnummer(stelnummer1);
    bil.setVognnummer(vognnummer1);
    bil.setUdstyrsNiveau("Active pack");
    bil.setCo2Udledning(122);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjcitroenc4")
  public String tilføjCitroenC4(@RequestParam int vognnummer2, @RequestParam int stelnummer2){
    Bil bil = new Bil();
    bil.setModel("Grand c4");
    bil.setMærke("Citröen");
    bil.setStelnummer(stelnummer2);
    bil.setVognnummer(vognnummer2);
    bil.setUdstyrsNiveau("Spacetourer cool");
    bil.setCo2Udledning(144);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjpeugeot2008aut")
  public String tilføjpeugeot2008aut(@RequestParam int vognnummer3, @RequestParam int stelnummer3){
    Bil bil = new Bil();
    bil.setModel("2008 AUT.");
    bil.setMærke("Peugeot");
    bil.setStelnummer(stelnummer3);
    bil.setVognnummer(vognnummer3);
    bil.setUdstyrsNiveau("Allure Pack AUT.");
    bil.setCo2Udledning(136);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjpeugeot2008sport")
  public String tilføjpeugeot2008sport(@RequestParam int vognnummer4, @RequestParam int stelnummer4){
    Bil bil = new Bil();
    bil.setModel("2008 Sport");
    bil.setMærke("Peugeot");
    bil.setStelnummer(stelnummer4);
    bil.setVognnummer(vognnummer4);
    bil.setUdstyrsNiveau("Selection Sport");
    bil.setCo2Udledning(127);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjpeugeot2008eat8")
  public String tilføjpeugeot2008ltd(@RequestParam int vognnummer5, @RequestParam int stelnummer5){
    Bil bil = new Bil();
    bil.setModel("2008 EAT8.");
    bil.setMærke("Peugeot");
    bil.setStelnummer(stelnummer5);
    bil.setVognnummer(vognnummer5);
    bil.setUdstyrsNiveau("Allure Pack EAT8.");
    bil.setCo2Udledning(125);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjopelcorsa")
  public String tilføjopelcorsa(@RequestParam int vognnummer6, @RequestParam int stelnummer6){
    Bil bil = new Bil();
    bil.setModel("Corsa");
    bil.setMærke("Opel");
    bil.setStelnummer(stelnummer6);
    bil.setVognnummer(vognnummer6);
    bil.setUdstyrsNiveau("Sport ltd.");
    bil.setCo2Udledning(120);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjopelcrossland")
  public String tilføjopelcrossland(@RequestParam int vognnummer7, @RequestParam int stelnummer7){
    Bil bil = new Bil();
    bil.setModel("Crossland");
    bil.setMærke("Opel");
    bil.setStelnummer(stelnummer7);
    bil.setVognnummer(vognnummer7);
    bil.setUdstyrsNiveau("Sport");
    bil.setCo2Udledning(147);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjpeugeot3008allure")
  public String tilføjpeugeot3008allure(@RequestParam int vognnummer8, @RequestParam int stelnummer8){
    Bil bil = new Bil();
    bil.setModel("3008");
    bil.setMærke("Peugeot");
    bil.setStelnummer(stelnummer8);
    bil.setVognnummer(vognnummer8);
    bil.setUdstyrsNiveau("Allure Pack AUT");
    bil.setCo2Udledning(135);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }

  @PostMapping("/tilføjds7")
  public String tilføjds7(@RequestParam int vognnummer9, @RequestParam int stelnummer9){
    Bil bil = new Bil();
    bil.setModel("7");
    bil.setMærke("DS");
    bil.setStelnummer(stelnummer9);
    bil.setVognnummer(vognnummer9);
    bil.setUdstyrsNiveau("Performance Line Pack");
    bil.setCo2Udledning(138);
    bil.setErDS(true);
    bilService.tilføjBil(bil);
    return "redirect:/";
  }
  //</editor-fold>
}
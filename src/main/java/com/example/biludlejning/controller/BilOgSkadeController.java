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

@Controller
public class BilOgSkadeController {
  BilService bilservice;
  SkadeService skadeService;

  public BilOgSkadeController(BilService bilservice, SkadeService skadeService){
    this.skadeService = skadeService;
    this.bilservice = bilservice;
  }

  @GetMapping("/unlimitedBiltyper")
  public String unlimitedBiltyper(Model model) {
    model.addAttribute("biler", bilservice.seBiler());
    model.addAttribute("modelAntal", bilservice.modelAntal());
    model.addAttribute("enAfHverModel", bilservice.enAfHverModel());
    return "unlimitedBiltyper";
  }

  @GetMapping("/limitedBiltyper")
  public String limitedBiltyper(HttpSession httpSession) {
    httpSession.getAttribute("brugerrolle");
    return "limitedBiltyper";
  }

  @GetMapping("/seSkadesrapporter")
  public String seSkadesrapporter(Model model){
    model.addAttribute("skadesrapporter",skadeService.seSkadesrapporter());
    return "seSkadesrapporter";
  }

  @GetMapping("/lavSkadesrapport")
  public String lavSkadesrapport(){
    return "lavSkadesrapport";
  }

  @PostMapping("/lavSkadesrapport")
  public String lavSkadesrapporten(@RequestParam String medarbejdernavn, @RequestParam String medarbejderemail,
                                   @RequestParam int vognnummer, @RequestParam String skade1, @RequestParam double pris1,
                                   @RequestParam String skade2, @RequestParam double pris2, @RequestParam String skade3,
                                   @RequestParam double pris3, @RequestParam int kundeid){
    Skadesrapport skadesrapport = new Skadesrapport();
    skadesrapport.setMedarbejderNavn(medarbejdernavn);
    skadesrapport.setMedarbejderEmail(medarbejderemail);
    skadesrapport.setVognnummer(vognnummer);
    LinkedHashMap<String,Double> skader = new LinkedHashMap<>();
    skader.put(skade1,pris1);
    skader.put(skade2,pris2);
    skader.put(skade3,pris3);
    skadesrapport.setSkader(skader);
    skadesrapport.setKundeId(kundeid);
    skadeService.lavSkadesrapport(skadesrapport);
    return "redirect:/";
  }

  @GetMapping("/seskadesrapport/{skadesrapportid}")
  public String seskadesrapport(@PathVariable("skadesrapportid") int skadesrapportId, Model model){
    model.addAttribute("skadesrapport",skadeService.findSkadesrapport(skadesrapportId));
    model.addAttribute("totalskadepris",skadeService.findTotalSkadePris(skadesrapportId));
    return "seskadesrapport";
  }

  @GetMapping("/tilføjbil")
  public String tilføjBil(){
    return "tilføjbil";
  }

  @PostMapping("/tilføjbil")
  public String tilføjbil(@RequestParam int vognnummer, @RequestParam int stelnummer, @RequestParam String mærke,
                          @RequestParam String model, @RequestParam String udstyrsniveau, @RequestParam int co2udledning){
    Bil bil = new Bil();
    bil.setVognnummer(vognnummer);
    bil.setStelnummer(stelnummer);
    bil.setMærke(mærke);
    bil.setModel(model);
    bil.setUdstyrsNiveau(udstyrsniveau);
    bil.setCo2Udledning(co2udledning);
    bilservice.tilføjBil(bil);
    return "redirect:/tilføjbil";
  }

}

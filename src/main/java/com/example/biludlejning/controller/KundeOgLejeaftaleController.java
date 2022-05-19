package com.example.biludlejning.controller;

import com.example.biludlejning.model.Kunde;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.service.KundeOgLejeaftaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class KundeOgLejeaftaleController {

  KundeOgLejeaftaleService kundeOgLejeaftaleService;

  public KundeOgLejeaftaleController(KundeOgLejeaftaleService kundeOgLejeaftaleService){
    this.kundeOgLejeaftaleService = kundeOgLejeaftaleService;
  }

  @GetMapping("/opretkunde")
  public String opretKunde() {
    return "opretkunde";
  }

  @PostMapping("/opretkunde")
  public String opretkunde(@RequestParam String navn, @RequestParam String email,
                           @RequestParam String adresse, @RequestParam int postnummer, @RequestParam String by){
    Kunde kunde = new Kunde(navn,email,adresse,postnummer,by);
    kundeOgLejeaftaleService.opretKunde(kunde);
    return "redirect:/";
  }
  @GetMapping("/kundeliste")
  public String kundeliste(Model model) {
    model.addAttribute("kunder", kundeOgLejeaftaleService.visAlleKunder());
    return "kundeliste";
  }

  @GetMapping("/opretlejeaftale")
  public String opretLejeaftale(Model model){
    model.addAttribute("kunder", kundeOgLejeaftaleService.visAlleKunder());
    model.addAttribute("hjemmebiler", kundeOgLejeaftaleService.seIkkeUdlejedeBiler());
    return "opretlejeaftale";
  }

  @PostMapping("/opretlejeaftale")
  public String opretLejekontrakt(@RequestParam int kundeid, @RequestParam int vognnummer, @RequestParam double forskudsbetaling,
                                  @RequestParam double månedligbetaling, @RequestParam int antalbetalinger,
                                  @RequestParam String slutlejedato, HttpSession httpSession){
    LejeAftale lejeAftale = new LejeAftale(kundeid,vognnummer,forskudsbetaling,månedligbetaling,antalbetalinger,slutlejedato);
    httpSession.setAttribute("lejekontrakt", kundeOgLejeaftaleService.lavLejeKontrakt(lejeAftale));
    kundeOgLejeaftaleService.lavLejeaftale(lejeAftale);
    return "redirect:/vislejekontrakt";
  }

  @GetMapping("/vislejekontrakt")
  public String vislejekontrakt(HttpSession httpSession){
    httpSession.getAttribute("lejekontrakt");
    return "/vislejekontrakt";
  }

}

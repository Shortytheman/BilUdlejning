package com.example.biludlejning.controller;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.model.LejeAftale;
import com.example.biludlejning.service.BrugerService;
import com.example.biludlejning.service.KundeOgLejeaftaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

  BrugerService brugerservice;
  KundeOgLejeaftaleService kundeOgLejeaftaleService;
  String fejlmeddelse = "";

  public HomeController(BrugerService brugerservice, KundeOgLejeaftaleService kundeOgLejeaftaleService) {
    this.brugerservice = brugerservice;
    this.kundeOgLejeaftaleService = kundeOgLejeaftaleService;
  }

  @GetMapping("/")
  public String index(HttpSession httpSession, Model model) {
    model.addAttribute("brugerRolle", httpSession.getAttribute("brugerRolle"));
    model.addAttribute("brugere",brugerservice.seBrugere());
    return "index";
  }

@GetMapping("/slet/{brugernavn}")
public String sletBruger(@PathVariable("brugernavn") String brugernavn){
    brugerservice.sletBruger(brugernavn);
  return "redirect:/";
}

  @GetMapping("/opretbruger")
  public String visBrugerOprettelse() {
    return "opretbruger";
  }

  @PostMapping("/opretbruger")
  public String opretBruger(@RequestParam String brugernavn, @RequestParam String rolle, @RequestParam String kodeord) {
    brugerservice.opretBruger(new Bruger(brugernavn, rolle, kodeord));
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String visLogin(HttpSession httpSession) {
    httpSession.getAttribute("fejlmeddelse");
    return "login";
  }

  @GetMapping("/logud")
  public String logud(HttpSession httpSession){
    httpSession.setAttribute("brugerRolle",null);
    httpSession.setAttribute("bruger",null);
    return "redirect:/";
  }

  @PostMapping("/login")
  public String login(@RequestParam String brugernavn, @RequestParam String kodeord,
                      HttpSession httpSession) {
    String returnStatement = "redirect:/";
    if (brugernavn.equalsIgnoreCase("admin") && kodeord.equalsIgnoreCase("admin")) {
      Bruger admin = new Bruger(brugernavn,"admin", kodeord);
      httpSession.setAttribute("brugerRolle", admin.getRolle());
      httpSession.setAttribute("bruger", admin);
    } else {
      Bruger bruger = brugerservice.findBruger(brugernavn);
      //Tjekker om brugernavn og kodeord hører sammen, derefter smider den brugeren ind på indexsiden til den tilhørende rolle.
      //Session bliver også oprettet med brugeren som logger på.
      if (brugerservice.korrektLogin(brugernavn, kodeord, bruger)) {
        httpSession.setAttribute("brugerRolle", bruger.getRolle());
        httpSession.setAttribute("bruger", bruger);
      } else returnStatement = "redirect:/login";
      //Hvis brugeren ikke findes, bliver der redirected, og her kan vi tilgå en model attribute "fejlmeddelse" der viser hvorfor.
      httpSession.setAttribute("fejlmeddelse", fejlmeddelse = brugerservice.loginFejl(bruger, kodeord));
    }
    return returnStatement;
  }

  @GetMapping("/unlimitedBiltyper")
  public String unlimitedBiltyper() {
    return "unlimitedBiltyper";
  }
  @GetMapping("/limitedBiltyper")
  public String limitedBiltyper() {
    return "limitedBiltyper";
  }

  @GetMapping("/opretkunde")
  public String opretKunde(){
    return "opretkunde";
  }

  @GetMapping("/opretlejeaftale")
  public String opretLejeaftale(@RequestParam int kundeid, @RequestParam int vognnummer, @RequestParam double forskudsbetaling,
                                 @RequestParam double månedligbetaling, @RequestParam int antalbetalinger, Model model){
    LejeAftale lejeAftale = new LejeAftale(kundeid,vognnummer,forskudsbetaling,månedligbetaling,antalbetalinger);
    kundeOgLejeaftaleService.lavLejeKontrakt(lejeAftale);
    model.addAttribute("lejekontrakt",kundeOgLejeaftaleService.lavLejeKontrakt(lejeAftale));
    return "opretlejeaftale";
  }

}
package com.example.biludlejning.controller;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.service.BrugerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//Hovedsageligt skrevet af Niklas

@Controller
public class HomeController {

  BrugerService brugerservice;

  /*Dependency Injection - Controller er afhængig af Brugerservice og ved DI, slipper controller for
  selv at skulle oprette objekter af denne klasse. */
  public HomeController(BrugerService brugerservice) {
    this.brugerservice = brugerservice;
  }

  @GetMapping("/sletbrugere")
  public String sletbrugere(Model model) {
    model.addAttribute("brugere", brugerservice.seBrugere());
    return "sletbrugere";
  }

  @GetMapping("/slet/{brugernavn}")
  public String sletBruger(@PathVariable("brugernavn") String brugernavn) {
    brugerservice.sletBruger(brugernavn);
    return "redirect:/sletbrugere";
  }

  @GetMapping("/opretbruger")
  public String visBrugerOprettelse() {
    return "opretbruger";
  }

  @PostMapping("/opretbruger")
  public String opretBruger(@RequestParam String brugernavn, @RequestParam String rolle, @RequestParam String kodeord) {
    //Opretter en bruger i DB - kun admin har rettighed til dette.
    brugerservice.opretBruger(new Bruger(brugernavn, rolle, kodeord));
    return "redirect:/";
  }

  //Skrevet af Frederik
  @GetMapping("/")
  public String visLogin(HttpSession httpSession, Model model) {
    model.addAttribute("brugere", brugerservice.seBrugere());
    httpSession.getAttribute("fejlmeddelse");
    return "index";
  }

  @GetMapping("/logud")
  public String logud(HttpSession httpSession) {
    httpSession.setAttribute("brugerRolle", null);
    httpSession.setAttribute("fejlmeddelse", "");
    return "redirect:/";
  }

  @PostMapping("/")
  public String login(@RequestParam String brugernavn, @RequestParam String kodeord,
                      HttpSession httpSession) {
    if (brugernavn.equalsIgnoreCase("admin") && kodeord.equalsIgnoreCase("admin")) {
      Bruger admin = new Bruger(brugernavn, "admin", kodeord);
      httpSession.setAttribute("brugerRolle", admin.getRolle());
    } else {
      Bruger bruger = brugerservice.findBruger(brugernavn);
      //Tjekker om brugernavn og kodeord hører sammen, derefter smider den brugeren ind på indexsiden til den tilhørende rolle.
      //Session bliver også oprettet med brugeren som logger på.
      if (brugerservice.korrektLogin(brugernavn, kodeord, bruger)) {
        httpSession.setAttribute("brugerRolle", bruger.getRolle());
      }
      //Hvis brugeren ikke findes, bliver der redirected, og her kan vi tilgå en model-attribute "fejlmeddelse" der viser hvorfor.
      httpSession.setAttribute("fejlmeddelse", brugerservice.loginFejl(bruger, kodeord));
    }
    return "redirect:/";
  }
}
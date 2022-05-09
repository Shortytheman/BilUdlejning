package com.example.biludlejning.controller;

import com.example.biludlejning.model.Bruger;
import com.example.biludlejning.service.BrugerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

  BrugerService brugerservice;
  String fejlmeddelse = "";

  public HomeController(BrugerService brugerservice){
    this.brugerservice = brugerservice;
  }

  @GetMapping("/")
  public String index(HttpSession httpSession, Model model) {
    model.addAttribute("brugerRolle", httpSession.getAttribute("brugerRolle"));
    return "index";
  }

  @PostMapping("/")
  public String indexOpretBruger(){
    return "redirect:/opretbruger";
  }

  @GetMapping("/opretbruger")
  public String visBrugerOprettelse(){
    return "opretbruger";
  }

  @PostMapping("/opretbruger")
  public String opretBruger(@RequestParam String brugernavn, @RequestParam String rolle, @RequestParam String kodeord){
  brugerservice.opretBruger(new Bruger(brugernavn,rolle,kodeord));
  return "redirect:/login";
  }

  @GetMapping("/login")
  public String visLogin(HttpSession httpSession) {
    httpSession.getAttribute("fejlmeddelse");
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String brugernavn, @RequestParam String kodeord,
                      HttpSession httpSession){
    Bruger bruger = brugerservice.findBruger(brugernavn);
    String returnStatement = "";
    //Tjekker om brugernavn og kodeord hører sammen, derefter smider den brugeren ind på indexsiden til den tilhørende rolle.
    //Session bliver også oprettet med brugeren som logger på.
    if (brugerservice.korrektLogin(brugernavn,kodeord,bruger)){
      returnStatement = "redirect:/";
      httpSession.setAttribute("brugerRolle",bruger.getRolle());
      httpSession.setAttribute("brugerNavn", bruger.getBrugernavn());
    } else returnStatement = "redirect:/login";
    //Hvis brugeren ikke findes, bliver der redirected, og her kan vi tilgå en model attribute "fejlmeddelse" der viser hvorfor.
    httpSession.setAttribute("fejlmeddelse",fejlmeddelse = brugerservice.loginFejl(bruger, kodeord));
    return returnStatement;
  }
}
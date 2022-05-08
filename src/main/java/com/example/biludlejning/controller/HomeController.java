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

  public HomeController(BrugerService brugerservice){
    this.brugerservice = brugerservice;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String visLogin(HttpSession httpSession) {
  return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String brugernavn, @RequestParam String kodeord,
                      HttpSession httpSession, Model model){
    String fejlmeddelse = "";
    Bruger bruger = brugerservice.findBruger(brugernavn);
    String returnStatement = "";
    //Tjekker om brugernavn og kodeord hører sammen, derefter smider den brugeren ind på en side alt efter deres rolle.
    //Session bliver også oprettet med brugeren som logger på.
    if (brugerservice.korrektLogin(brugernavn,kodeord,bruger)){
      returnStatement = "redirect:/" + bruger.getRolle();
      httpSession.setAttribute("bruger",bruger);
    } else returnStatement = "redirect:/";
    //Hvis brugeren ikke findes, bliver der redirected, og her kan vi tilgå en model attribute "fejlmeddelse" der viser hvorfor.
    fejlmeddelse = brugerservice.loginFejl(bruger, kodeord);
    model.addAttribute("fejlmeddelse", fejlmeddelse);
    return returnStatement;
  }
}


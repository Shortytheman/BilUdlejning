package com.example.biludlejning.controller;

import com.example.biludlejning.service.BilService;
import com.example.biludlejning.service.SkadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
  public String lavSkadesrapporten(){
    return "redirect:/";
  }

  @GetMapping("/seskadesrapport/{skadesrapportid}")
  public String seskadesrapport(@PathVariable("skadesrapportid") int skadesrapportId, Model model){
    model.addAttribute("skadesrapport",skadeService.findSkadesrapport(skadesrapportId));
    return "seskadesrapport";
  }

}

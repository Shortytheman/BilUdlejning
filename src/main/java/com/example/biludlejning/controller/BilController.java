package com.example.biludlejning.controller;

import com.example.biludlejning.service.BilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BilController {
  BilService bilservice;

  public BilController(BilService bilservice){
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

  @GetMapping("/seSkadesrapport")
  public String seSkadesrapport(){
    return "seSkadesrapport";
  }

  @GetMapping("/lavSkadesrapport")
  public String lavSkadesrapport(){
    return "lavSkadesrapport";
  }

  @PostMapping("/lavSkadesrapport")
  public String lavSkadesrapporten(){
    return "redirect:/";
  }
}

package com.erayozer.kicktipp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Try {
    @GetMapping("/try")
    public String tryOut() {
        return "index";
    }
}

package co.boldtec.salesgen.infra.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomepageController {

    @GetMapping(value = "/")
    public String HomePage() {
        return "home";
    }
}

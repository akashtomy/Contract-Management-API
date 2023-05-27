package com.bhagwati.ContractManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/agreements")
public class AgreementController {

    @GetMapping
    public String getAgreement() {
        return "Agreement";
    }
}


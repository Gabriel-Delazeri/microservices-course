package com.delazeri.cambioservice.controllers;

import com.delazeri.cambioservice.models.Cambio;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "cambio-service")
public class CambioController {

    private final Environment environment;

    public CambioController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/{amount}/{from}/{to}")
    public ResponseEntity<Cambio> getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    ) {
        String port = environment.getProperty("local.server.port");

        return ResponseEntity.ok(new Cambio(1L, from, to, BigDecimal.ONE, amount, port));
    }
}

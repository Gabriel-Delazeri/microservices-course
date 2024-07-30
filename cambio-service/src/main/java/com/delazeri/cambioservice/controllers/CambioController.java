package com.delazeri.cambioservice.controllers;

import com.delazeri.cambioservice.models.Cambio;
import com.delazeri.cambioservice.repositories.CambioRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping(value = "cambio-service")
public class CambioController {

    private final Environment environment;
    private final CambioRepository cambioRepository;

    public CambioController(Environment environment, CambioRepository cambioRepository) {
        this.environment = environment;
        this.cambioRepository = cambioRepository;
    }

    @GetMapping(value = "/{amount}/{from}/{to}")
    public ResponseEntity<Cambio> getCambio(
            @PathVariable BigDecimal amount,
            @PathVariable String from,
            @PathVariable String to
    ) {
        Cambio cambio = cambioRepository.findByFromAndTo(from, to);
        if (cambio == null) throw new RuntimeException("Currency unsupported");

        String port = environment.getProperty("local.server.port");
        cambio.setEnvironment(port);

        BigDecimal conversionFactor = cambio.getConversionFactor();

        cambio.setConvertedValue(conversionFactor.multiply(amount).setScale(2, RoundingMode.CEILING));

        return ResponseEntity.ok(cambio);
    }
}

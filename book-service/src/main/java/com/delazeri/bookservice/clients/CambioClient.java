package com.delazeri.bookservice.clients;

import com.delazeri.bookservice.dtos.responses.CambioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service")
public interface CambioClient {

    @GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
    CambioDto getCambio(
            @PathVariable Double amount,
            @PathVariable String from,
            @PathVariable String to
    );
}

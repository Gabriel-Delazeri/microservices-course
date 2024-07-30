package com.delazeri.greetingservice.controllers;

import com.delazeri.greetingservice.configuration.GreetingConfiguration;
import com.delazeri.greetingservice.models.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final GreetingConfiguration greetingConfiguration;

    public GreetingController(GreetingConfiguration greetingConfiguration) {
        this.greetingConfiguration = greetingConfiguration;
    }

    private static final String template = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "greeting")
    public ResponseEntity<Greeting> getGreeting(
            @RequestParam(name = "name", defaultValue = "World") String name
    ) {
        if (name.isEmpty()) name = greetingConfiguration.getDefaultValue();

        Greeting greeting = new Greeting(counter.incrementAndGet(),
                template.formatted(greetingConfiguration.getGreeting(), name)
        );

        return ResponseEntity.ok(greeting);
    }
}

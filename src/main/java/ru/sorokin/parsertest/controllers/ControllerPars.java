package ru.sorokin.parsertest.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.sorokin.parsertest.service.ParsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControllerPars {
    private final WebClient webClient;
    private final ParsService parsService;

    @GetMapping("/novo")
    public String getParsNovo() {
        String outResponse;

        Mono<String> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/elitegis/rest/services/maps/ritual/MapServer/1/query").build())
                .retrieve()
                .bodyToMono(String.class);
        outResponse = response.block();

        return outResponse;
    }

    @GetMapping("/novo/features")
    public String getFeatures() {
        return parsService.parseFeatures();
    }

}

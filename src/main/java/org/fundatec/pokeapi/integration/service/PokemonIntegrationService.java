package org.fundatec.pokeapi.integration.service;

import org.fundatec.pokeapi.integration.response.PokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class PokemonIntegrationService {

    private final RestTemplate restTemplate;

    @Value("$(pokemon-external-api)")
    private String uri;
    public PokemonIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonResponse buscarPokemonNoServicoExternoPeloNome(String nome) {
        String urlCompleta = this.uri + "/" + nome;

        PokemonResponse pokemonExterno = this.restTemplate.getForObject(urlCompleta, PokemonResponse.class);
        return pokemonExterno;
    }
}

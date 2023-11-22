package org.fundatec.pokeapi.integration.service;

import org.fundatec.pokeapi.integration.response.PokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class PokemonIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${pokemon-external-api}")
    private String uri;
    public PokemonIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PokemonResponse buscarPokemonNoServicoExternoPeloNome(String nome) {
        String urlCompleta = this.uri + "/" + nome;

        PokemonResponse pokemonExterno = this.restTemplate.getForObject(urlCompleta, PokemonResponse.class);

        String nomePokemonAPIExterna = pokemonExterno.getName();
        String inicialPokemon = String.valueOf(nomePokemonAPIExterna.charAt(0));
        String inicialPokemonToUpperCase = inicialPokemon.toUpperCase();
        String nomeToUpperCase = nomePokemonAPIExterna.replace(inicialPokemon, inicialPokemonToUpperCase);
        pokemonExterno.setName(nomeToUpperCase);
        return pokemonExterno;
    }
}

package poke.api.integration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import poke.api.integration.response.PokemonResponse;

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
        String nomePokemonApiExterna = pokemonExterno.getName();
        tornarPrimeiraLetraEmMaiuscula(pokemonExterno, nomePokemonApiExterna);
        return pokemonExterno;
    }

    private static void tornarPrimeiraLetraEmMaiuscula(PokemonResponse pokemonExterno, String nomePokemonApiExterna) {
        String primeiraLetra = nomePokemonApiExterna.substring(0, 1);
        String novaPrimeiraLetra = primeiraLetra.toUpperCase();
        pokemonExterno.setName(nomePokemonApiExterna.replace(primeiraLetra, novaPrimeiraLetra));
    }

}

  package org.fundatec.pokeapi.controller;

import org.apache.coyote.Request;
import org.fundatec.pokeapi.integration.response.PokemonResponse;
import org.fundatec.pokeapi.integration.service.PokemonIntegrationService;
import org.fundatec.pokeapi.model.Pokemon;
import org.fundatec.pokeapi.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("v1/pokemons")
public class PokemonController {

    private PokemonService service;
    private PokemonIntegrationService integrationService;
    public PokemonController(PokemonService service) {
        this.service = service;
        this.integrationService = integrationService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> retornarTodosOsPokemons() {
        return ResponseEntity.ok(service.retornarTodos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Void> adicionarPokemon(@RequestBody Pokemon pokemon) {
        service.adicionar(pokemon);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPokemonPorID(@PathVariable("id") Long id) {
        service.removerPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> removerPokemonPorNome(@PathVariable("nome") String nome) {
        service.removerPorNome(nome);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/nome/tipo/{nome}/{tipo}")


    @GetMapping("/api-externa/{nome}")
    public ResponseEntity<PokemonResponse> buscarPokemonNoServicoExterno(@PathVariable("nome") String nome) {
        PokemonResponse pokemonBuscadoServicoExterno =
                this.integrationService.buscarPokemonNoServicoExternoPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscadoServicoExterno);
    }


}

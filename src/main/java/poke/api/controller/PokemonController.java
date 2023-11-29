package poke.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.api.integration.response.PokemonResponse;
import poke.api.integration.service.PokemonIntegrationService;
import poke.api.model.Pokemon;
import poke.api.model.dto.PokemonRequestDto;
import poke.api.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("api/v1/pokemons")
public class PokemonController {

    private PokemonService pokemonService;
    private PokemonIntegrationService pokemonIntegrationService;

    public PokemonController(PokemonService pokemonService, PokemonIntegrationService pokemonIntegrationService) {
        this.pokemonService = pokemonService;
        this.pokemonIntegrationService = pokemonIntegrationService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarNumeroDePokemons(){
        return ResponseEntity.ok(this.pokemonService.contar());
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> buscarTodosPokemons() {
        return ResponseEntity.ok(this.pokemonService.buscarTodos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPeloNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(this.pokemonService.buscarPokemonPorNome(nome));
    }

    @PostMapping()
    public ResponseEntity<Void> adicionarPokemons(@RequestBody PokemonRequestDto pokemon) {
        pokemonService.adicionar(pokemon);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> removerPokemonsPorId(@PathVariable("id") Long id) {
        Pokemon pokemonRemovido = pokemonService.removerPorId(id);
        return ResponseEntity.ok(pokemonRemovido);
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Pokemon> removerPokemonPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(this.pokemonService.removerPorNome(nome));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Pokemon> alterarPokemonPorId(@PathVariable("id") Long id,
                                                       @RequestBody Pokemon pokemon) {
        Pokemon pokemonAlterado = pokemonService.alterarPorId(id, pokemon);
        return ResponseEntity.ok(pokemonAlterado);
    }

    @GetMapping("/api-externa/{nome}")
    public ResponseEntity<PokemonResponse> buscarPokemonNoServicoExterno(@PathVariable("nome") String nome) {
        PokemonResponse pokemonBuscadoServicoExterno =
                this.pokemonIntegrationService.buscarPokemonNoServicoExternoPeloNome(nome);
        return ResponseEntity.ok(pokemonBuscadoServicoExterno);
    }
}

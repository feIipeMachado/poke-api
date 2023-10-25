package org.fundatec.pokeapi.controller;

import org.fundatec.pokeapi.model.Pokemon;
import org.fundatec.pokeapi.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    private PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> retornarTodosOsPokemons() {
        return ResponseEntity.ok(service.retornarTodos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pokemon> buscarPokemonPorNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }
}

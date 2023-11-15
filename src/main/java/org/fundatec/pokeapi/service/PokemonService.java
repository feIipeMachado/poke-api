package org.fundatec.pokeapi.service;

import org.fundatec.pokeapi.model.Pokemon;
import org.fundatec.pokeapi.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }
    public List<Pokemon> retornarTodos() {
        return repository.findAll();
    }

    public Pokemon buscarPorNome(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }

    public void adicionar(Pokemon pokemon) {
        repository.save(pokemon);
    }

    public Pokemon removerPorId(Long id) {
        Pokemon pokemon = repository.findById(id).get();
        repository.delete(pokemon);
        return pokemon;
    }

    public Pokemon removerPorNome(String nome) {
        Pokemon pokemon = repository.findByNomeIgnoreCase(nome);
        repository.delete(pokemon);
        return pokemon;
    }

    public Pokemon editarNomeTipo (Long id, String nome, String tipo) {
        Pokemon pokemon = repository.findById(id).get();
        pokemon.setNome(nome);
        pokemon.setTipo(tipo);
        return pokemon;
    }


//\
}

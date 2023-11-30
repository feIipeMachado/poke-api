package poke.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import poke.api.model.Pokemon;
import poke.api.model.dto.PokemonRequestDto;
import poke.api.model.dto.converter.PokemonConverter;
import poke.api.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> buscarTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon buscarPokemonPorNome(String nome) {
        Pokemon pokemonBuscado = pokemonRepository.findByNome(nome);
        if (pokemonBuscado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokémon " + nome + " não existe");
        } else {
            return pokemonBuscado;
        }
    }

    public List<Pokemon> buscarPokemonPorTipo(String tipo) {
        List<Pokemon> pokemonsBuscados = pokemonRepository.findByTipo(tipo);
        if (pokemonsBuscados.stream().count() == 0 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo " + tipo + " não existe");
        } else {
            return pokemonsBuscados;
        }
    }

    public void adicionar(PokemonRequestDto pokemon) {
        String nomePokemonAdicionado = pokemon.getNome();
        Pokemon pokemonExistente = pokemonRepository.findByNome(nomePokemonAdicionado);

        if (pokemonExistente != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokémon " + nomePokemonAdicionado + " já existe");
        } else {
            Pokemon pokemonEntidade = PokemonConverter.converterPraEntidade(pokemon);

            pokemonRepository.save(pokemonEntidade);

        }
    }

    public Pokemon removerPorId(Long id) {
        Optional<Pokemon> pokemonParaRemover = pokemonRepository.findById(id);
        if (pokemonParaRemover.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokémon com id " + id + " não existe");
        } else {
            pokemonRepository.delete(pokemonParaRemover.get());
            return pokemonParaRemover.get();
        }
    }

    public Pokemon removerPorNome(String nome) {
        Pokemon pokemonParaRemover = pokemonRepository.findByNome(nome);
        if (pokemonParaRemover == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokémon " + nome + " não existe");
        } else {
            pokemonRepository.delete(pokemonParaRemover);
            return pokemonParaRemover;
        }
    }

    public Long contar() {
        return pokemonRepository.count();
    }

    public Pokemon alterarPorId(Long id, Pokemon pokemon) {
        Pokemon pokemonEncontrado = pokemonRepository.findById(id).get();
        pokemonEncontrado.setNome(pokemon.getNome());
        pokemonEncontrado.setTipo(pokemon.getTipo());
        pokemonRepository.save(pokemonEncontrado);
        return pokemonEncontrado;
    }
}

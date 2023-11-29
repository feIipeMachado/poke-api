package poke.api.model.dto.converter;

import poke.api.model.Pokemon;
import poke.api.model.dto.PokemonRequestDto;

public class PokemonConverter {

    public static Pokemon converterPraEntidade(PokemonRequestDto pokemonDto) {
        Pokemon pokemonEntidade = new Pokemon();
        pokemonEntidade.setNome(pokemonDto.getNome());
        pokemonEntidade.setTipo(pokemonDto.getTipo());
        pokemonEntidade.setAltura(pokemonDto.getAltura());
        pokemonEntidade.setPeso(pokemonDto.getPeso());
        pokemonEntidade.setLevel(pokemonDto.getLevel());

        return pokemonEntidade;
    }
}

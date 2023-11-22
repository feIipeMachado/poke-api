package org.fundatec.pokeapi.integration.response;

import lombok.Data;
import org.springframework.boot.convert.Delimiter;


@Data

public class MovesResponse {

    private MoveResponse move;
}

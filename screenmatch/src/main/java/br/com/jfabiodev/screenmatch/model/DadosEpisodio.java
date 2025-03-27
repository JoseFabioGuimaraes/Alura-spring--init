package br.com.jfabiodev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Season") Integer season,
                            @JsonAlias("Episode") Integer numeroEp,
                            @JsonAlias("imdbRating") String IMDBAvaliacao,
                            @JsonAlias("Released") String dataLancamento) {
}

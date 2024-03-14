package br.com.alura.screenmatch.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar todos os dados que não foram listados para busca.
public record EpisodeDatas(@JsonAlias("Title") String title,
                           @JsonAlias("Episode") Integer episode,
                           @JsonAlias("imdbRating") String rating,
                           @JsonAlias("Released") String release) {
}

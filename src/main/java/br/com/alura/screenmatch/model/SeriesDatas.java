package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar todos os dados que não foram listados para busca.
public record SeriesDatas(@JsonAlias("Title") String title,
                          @JsonAlias("totalSeasons") Integer totalSeason,
                          @JsonAlias("imdbRating") String rating,
                          @JsonAlias("Genre") String genre,
                          @JsonAlias("Actors") String actors,
                          @JsonAlias("Poster") String poster,
                          @JsonAlias("Plot") String plot) {

}

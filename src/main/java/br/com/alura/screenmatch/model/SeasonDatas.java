package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignorar todos os dados que não foram listados para busca.
public record SeasonDatas(@JsonAlias("Season") Integer season,
                          @JsonAlias("Episodes") List<EpisodeDatas> episodesList) {

}

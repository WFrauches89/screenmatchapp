package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.enums.Category;

public record SeriesDTO( Long id,
                         String title,
                         Integer totalSeason,
                         Double rating,
                         Category genre,
                         String actors,
                         String poster,
                         String plot) {

}

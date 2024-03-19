package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Series;
import jakarta.persistence.*;

import java.time.LocalDate;

public record EpisodeDTO(Integer season,
                         Integer numberEpisode,
                         String title) {
}

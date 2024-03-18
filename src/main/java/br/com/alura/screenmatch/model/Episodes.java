package br.com.alura.screenmatch.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Episodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate release;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Series series;


    public Episodes(Integer numberSeason, EpisodeDatas episodeDatas) {
        this.season = numberSeason;
        this.numberEpisode = episodeDatas.episode();
        this.title = episodeDatas.title();

        try{
            this.rating = Double.valueOf(episodeDatas.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }
        try {
            this.release = LocalDate.parse(episodeDatas.release());
        } catch (DateTimeParseException ex) {
            this.release = null;
        }
    }

    @Override
    public String toString() {
        return  "Temporada= " + season +
                ", Número do episódio= " + numberEpisode +
                ", Título= " + title + '\'' +
                ", avaliacao= " + rating +
                ", data de lancamento= " + release;
    }
}

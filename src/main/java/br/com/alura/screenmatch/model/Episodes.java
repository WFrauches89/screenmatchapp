package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodes {

    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate release;

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

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
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

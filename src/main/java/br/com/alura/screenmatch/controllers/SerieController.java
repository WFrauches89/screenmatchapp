package br.com.alura.screenmatch.controllers;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.model.Episodes;
import br.com.alura.screenmatch.model.enums.Category;
import br.com.alura.screenmatch.services.SeriesService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@Builder
@AllArgsConstructor
@RequestMapping("/series")
public class SerieController {

    private SeriesService seriesService;

    @GetMapping
    public List<SeriesDTO> getListSeries(){
        return seriesService.obterSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> getListTop5(){
        return seriesService.getListTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SeriesDTO> getLancamento() {
        return seriesService.getListLancamento() ;
    }

    @GetMapping("/{id}")
    public SeriesDTO getById(@PathVariable Long id) {
        return seriesService.getByID(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeasons (@PathVariable Long id) {
        return seriesService.getAllSeasonsService(id);
    }

    @GetMapping("/{id}/temporadas/{season}")
    public List<EpisodeDTO> getEpisodesFromS (@PathVariable Long id, @PathVariable Integer season){
        return seriesService.getEpisodesFromSeasonServ(id, season);
    }

    @GetMapping("/categoria/{categoryName}")
    public List<SeriesDTO> getSeriesFromCategory(@PathVariable String categoryName){
        return seriesService.getSeriesCategory(categoryName);
    }

}

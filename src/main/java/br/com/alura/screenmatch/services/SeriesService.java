package br.com.alura.screenmatch.services;

import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SeriesDTO> obterSeries(){
        return convertSerieToSerieDTO(serieRepository.findAll());
    }


    public List<SeriesDTO> getListTop5Series() {
        return convertSerieToSerieDTO(serieRepository.findAll())
                .stream()
                .sorted(Comparator.comparingDouble(SeriesDTO::rating).reversed())
                .limit(5).toList();
    }
    public List<SeriesDTO> getListLancamento() {
        return convertSerieToSerieDTO(serieRepository.encontrarEpisodiosRecentes());
    }
    public SeriesDTO getByID(Long id)  {
        Optional<Series> series = serieRepository.findById(id);

        if(series.isPresent()) {
            Series s = series.get();
            return new SeriesDTO(s.getId(),s.getTitle(),s.getTotalSeason(),s.getRating(),s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
        } else {
            return null;
//            throw new Exception(new FileNotFoundException());
        }
    }

    private List<SeriesDTO> convertSerieToSerieDTO (List<Series> series) {
        return series.stream()
                .map(s -> new SeriesDTO(s.getId(),s.getTitle(),s.getTotalSeason(),s.getRating(),s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()))
                .toList();
    }

}

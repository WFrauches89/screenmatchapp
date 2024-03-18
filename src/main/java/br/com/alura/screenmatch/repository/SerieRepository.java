package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodes;
import br.com.alura.screenmatch.model.Series;
import br.com.alura.screenmatch.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainingIgnoreCase(String title);

    List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, Double avaliacao);

    List<Series> findByGenre(Category category);

    List<Series> findBytotalSeasonLessThanEqualAndRatingGreaterThanEqual(Integer seasons, Double avaliacao);

    @Query("select s FROM Series s WHERE s.totalSeason <= :seasons AND s.rating >= :avaliacao")
    List<Series> seriesSeasonAndrating(Integer seasons, Double avaliacao);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:trecho%")
    List<Episodes> episodioPorTrecho(String trecho);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s= :sB ORDER BY e.rating DESC LIMIT :limit ")
    List<Episodes> topEpisodesSerie(Series sB, int limit);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :serie AND YEAR(e.release) >= :ano ")
    List<Episodes> dataLimit(Series serie,int ano);

    List<Series> findTop5ByOrderByEpisodesReleaseDesc();

    @Query("SELECT s FROM Series s " +
            "JOIN s.episodes e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.release) DESC LIMIT 5")
    List<Series> encontrarEpisodiosRecentes();

}

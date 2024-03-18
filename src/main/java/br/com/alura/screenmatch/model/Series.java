package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.model.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


@Entity
@Table(name="series")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    @Column
    private Integer totalSeason;
    @Column
    private Double rating;
    @Column
    @Enumerated(EnumType.STRING)
    private Category genre;
    @Column
    private String actors;
    @Column
    private String poster;
    @Column
    private final String plot;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodes> episodes = new ArrayList<>();

    public Series(SeriesDatas seriesDatas) {
        this.title = seriesDatas.title();
        this.totalSeason = seriesDatas.totalSeason();
        this.rating = OptionalDouble.of(Double.parseDouble(seriesDatas.rating())).orElse(0.0);
        this.genre = Category.fromString(seriesDatas.genre().split(",")[0].trim());
        this.actors = seriesDatas.actors();
        this.poster = seriesDatas.poster();
        this.plot = seriesDatas.plot();
//        this.plot = ConsultaChatGPT.obterTraducao(seriesDatas.plot()).trim();
    }

    public void setEpisodes(List<Episodes> episodes) {
        episodes.forEach(s -> s.setSeries(this));
        this.episodes = episodes;
    }

    @Contract(pure = true)
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "genre=" + genre +
                ", title='" + title + '\'' +
                ", totalSeason=" + totalSeason +
                ", rating=" + rating +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", plot='" + plot + '\'';
//                ", episodes='" + episodes + '\'';
    }
}

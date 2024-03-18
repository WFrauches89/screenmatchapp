package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.model.enums.Category;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.services.ConsumerAPI;
import br.com.alura.screenmatch.services.DataConvert;
import lombok.*;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Principal {

private Scanner getInsert = new Scanner(System.in);
private final String ADDRESS = "https://www.omdbapi.com/?t=";
private final String API_KEY = "&apikey=c9d230be";
private ConsumerAPI consumerAPI = new ConsumerAPI();
private DataConvert dataConvert = new DataConvert();
private List<SeriesDatas> seriesDatas = new ArrayList<>();
private List<Series> sList = new ArrayList<>();
private Optional<Series> serieBuscada;

    private SerieRepository serieRepository;


    public Principal(SerieRepository serieRepository) {
        this.serieRepository =serieRepository;

    }


    public void menu() {
    var choice = -1;
    while (choice != 0) {

        var menu = """
                ********************** Opções **********************
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries pesquisadas
                4 - Buscar por título
                5 - Buscar por ator
                6 - Top series
                7 - Buscar por categoria
                8 - Buscar por tamanho temporada e avaliação
                9 - Buscar Episódios
                10 - Top Episódios Série
                11 - Buscar por data lançamento
                                
                0 - Sair                               
                """;

        System.out.println(menu);
        System.out.println("Escolha sua opção: ");
        choice = getInsert.nextInt();
        getInsert.nextLine();

        switch (choice) {
            case 1 -> buscarSerieWeb();
            case 2 -> buscarEpisodioPorSerie();
            case 3 -> serieList();
            case 4 -> buscarPorTitle();
            case 5 -> buscarPorAtor();
            case 6 -> buscarTopSeries();
            case 7 -> buscarPorCategoria();
            case 8 -> buscarTamanhoSeasonsAndRating();
            case 9 -> buscarEpisodioPorTrecho();
            case 10 -> buscarEpisodiosTopSerie();
            case 11 -> buscarPorDataInicial();
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida");
        }
    }
}

    private void buscarPorDataInicial() {
        buscarPorTitle();
        System.out.println("Qual ano inicial para busca? ");
        var ano = getInsert.nextInt();
        if(serieBuscada.isPresent()){
            Series sB = serieBuscada.get();
            List<Episodes> topEp = serieRepository.dataLimit(sB,ano);
            topEp.forEach(e -> System.out.println(
                    e.getTitle() + " " + e.getRating()+ " - Season: "+ e.getSeason()
            ));
        }
    }

    private void buscarEpisodiosTopSerie() {
        buscarPorTitle();
        System.out.println("Qual será a quantidade do top? ");
        var limit = getInsert.nextInt();
        if(serieBuscada.isPresent()){
            Series sB = serieBuscada.get();
            List<Episodes> topEp = serieRepository.topEpisodesSerie(sB,limit);
            topEp.forEach(e -> System.out.println(
                e.getTitle() + " " + e.getRating()+ " - Season: "+ e.getSeason()
            ));
        }

    }

    private void buscarEpisodioPorTrecho() {

        System.out.println("Digite o trecho do episódio desejado: ");
        var trecho = getInsert.nextLine();
        serieRepository.episodioPorTrecho(trecho)
                .forEach(e ->
                        System.out.printf("Série: %s Temporada: %s - Episódio %s - %s\n",
                                e.getSeries().getTitle(),
                                e.getSeason(),
                                e.getNumberEpisode(),
                                e.getTitle()));

    }

    private void buscarTamanhoSeasonsAndRating() {
        System.out.println("Você quer ver séries com até quantas temporadas?");
        var tamanhoSeason = getInsert.nextInt();
        System.out.println("E qual a a avaliação mínima? ");
        var ratingMin = getInsert.nextDouble();
        System.out.println("*** Séries filtradas ***");
        serieRepository.seriesSeasonAndrating(tamanhoSeason,ratingMin)
                .forEach(System.out::println);



    }

    private void buscarPorCategoria() {
        System.out.println("Qual o gênero desejado? ");
        var nameGenre = getInsert.nextLine();
        Category category = Category.fromString(nameGenre);
        serieRepository.findByGenre(category)
                .forEach(System.out::println);
    }

    private void buscarTopSeries() {
        System.out.println("Quantidade de séries do top...?");
        var qtdSerie = getInsert.nextInt();
        sList = serieRepository.findAll();

        sList.stream()
                .sorted(Comparator.comparing(Series::getRating).reversed())
                .limit(qtdSerie)
                .forEach(s -> System.out.println(s.getTitle()+" "+s.getRating()));

    }

    private void buscarPorAtor() {
        System.out.println("Digite o nome do ator/atriz desejada: ");
        var actorName = getInsert.nextLine();
        System.out.println("Qual o mínimo de avaliação?");
        var avalia = getInsert.nextDouble();
        List<Series> seriePorAtorBuscado = serieRepository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName, avalia);

        System.out.println("Séries que "+actorName+ " trabalhou...");
        seriePorAtorBuscado.forEach(s -> System.out.println(s.getTitle()+ " " + s.getRating()));
    }

    private void buscarPorTitle() {
        System.out.println("Digite o nome da série desejada: ");
        var serieName = getInsert.nextLine();
        serieBuscada = serieRepository.findByTitleContainingIgnoreCase(serieName);

        if(serieBuscada.isPresent()){
            System.out.println("Dados da série: "+ serieBuscada.get());
        }else {
            System.out.println("Série não encontrada");
        }

    }

    private void buscarSerieWeb() {
        SeriesDatas sDatas = getSeriesDatas();
        Series series = new Series(sDatas);
        serieRepository.save(series);
        System.out.println(sDatas);
        }

private void serieList() {
        sList = serieRepository.findAll();

        sList.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);
}


private SeriesDatas getSeriesDatas() {
        System.out.println("Digite o nome da série desejada: ");
        var serieName = getInsert.nextLine();
        var json = consumerAPI.getDados(ADDRESS + serieName.replace(" ", "+") + API_KEY);
        return dataConvert.obterDatas(json, SeriesDatas.class);
        }

private void buscarEpisodioPorSerie() {
    serieList(); //listar séries
    System.out.println("Qual a série escolhida para buscar os episódios?");
    String nameSerie = getInsert.nextLine();
    var optionalSeries = serieRepository.findByTitleContainingIgnoreCase(nameSerie);

    if (optionalSeries.isPresent()) {
        var serieEncontrada = optionalSeries.get(); //convertendo de um optional para Serie(objeto)
        List<SeasonDatas> seasonsDataList = new ArrayList<>();

        for (int i = 1; i <= serieEncontrada.getTotalSeason(); i++) {
            var json = consumerAPI.getDados(ADDRESS + serieEncontrada.getTitle().replace(" ", "+") + "&season=" + i + API_KEY);
            SeasonDatas seasonDatas = dataConvert.obterDatas(json, SeasonDatas.class);
            seasonsDataList.add(seasonDatas);
        }
        seasonsDataList.forEach(System.out::println);

        var collectEpisodesFromSeasonToSerie = seasonsDataList.stream()
                .flatMap(d -> d.episodesList().stream()
                        .map(e -> new Episodes(d.season(), e)))
                .collect(Collectors.toList());

        serieEncontrada.setEpisodes(collectEpisodesFromSeasonToSerie);

        System.out.println("vai salvar!");
        serieRepository.save(serieEncontrada);

    }else {
        System.out.println("Série não encontrada...");
    }

}




}

package br.com.jfabiodev.screenmatch.main;

import br.com.jfabiodev.screenmatch.model.DadosEpisodio;
import br.com.jfabiodev.screenmatch.model.DadosSerie;
import br.com.jfabiodev.screenmatch.model.DadosTemporadas;
import br.com.jfabiodev.screenmatch.model.Episodio;
import br.com.jfabiodev.screenmatch.service.ConsumoAPI;
import br.com.jfabiodev.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumo = new ConsumoAPI();
    private final ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f9173cff";

    public void exibeMenu(){
        System.out.println("Digite o nome da Série: ");
        String nomeSerie = scanner.nextLine();
        var consumoAPI = new ConsumoAPI();
        String json = consumo.obterDados(ENDERECO+ nomeSerie.replace(" ", "+") +API_KEY);
        DadosSerie dadosSerie = converteDados.obterDados(json,DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i<= dadosSerie.totalTemporadas(); i++){
            json = consumoAPI.obterDados(ENDERECO+ nomeSerie.replace(" ", "+")+"&season=" +i +API_KEY);
            DadosTemporadas temporada = converteDados.obterDados(json, DadosTemporadas.class);
            temporadas.add(temporada);
        }
        temporadas.forEach(System.out::println);

        for (int i = 0; i < dadosSerie.totalTemporadas() ; i++) {
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size() ; j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t ->t.episodios().forEach(e -> System.out.println(e.titulo())));

//        List<String> nomes = Arrays.asList("Fabio","Guimarães","Filho");
//        nomes.stream().sorted().limit(3).filter(n -> n.startsWith("G"))
//                .map(String::toUpperCase)
//                .forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap( t -> t.episodios().stream())
                .collect(Collectors.toList());

//        System.out.println("Top 10 eps: ");
//        dadosEpisodios.stream()
//                .filter(e -> !e.IMDBAvaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primeiro filtro (teste)" + e))
//                .sorted(Comparator.comparing(DadosEpisodio::IMDBAvaliacao).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limit " + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episodio>episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

        System.out.println("Digite o nome do titulo que deseja: ");
        var nomeTitulo = scanner.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(nomeTitulo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado: Temporada: " + episodioBuscado.get().getTemporada() + " Episodio: " +episodioBuscado.get().getNumeroEpisodio());
        } else {
            System.out.println("Episódio não encontrado");
        }

//        System.out.println("A partir de que ano os episodios devem ser exibidos?");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(" Temporada: "+ e.getTemporada()
//                + " Episodio: " + e.getTitulo()
//                + " Data de lançamento: " + e.getDataLancamento().format(formatter)
//                ));

        Map<Integer,Double> ratingPerSeason = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(ratingPerSeason);

        DoubleSummaryStatistics stats = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Média: "+ stats.getAverage() + " Melhor episodio: " + stats.getMax()
        + " Pior episodio:  " + stats.getMin() + " Quantidade de episodios avalaiados: " + stats.getCount());
    }
}

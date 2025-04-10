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

        System.out.println("Top 5 eps: ");
        dadosEpisodios.stream()
                .filter(e -> !e.IMDBAvaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::IMDBAvaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio>episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano os episodios devem ser exibidos?");
        var ano = scanner.nextInt();
        scanner.nextLine();
        LocalDate dataBusca = LocalDate.of(ano,1,1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(" Temporada: "+ e.getTemporada()
                + " Episodio: " + e.getTitulo()
                + " Data de lançamento: " + e.getDataLancamento().format(formatter)
                ));
    }
}

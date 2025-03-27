package br.com.jfabiodev.screenmatch.main;

import br.com.jfabiodev.screenmatch.model.DadosEpisodio;
import br.com.jfabiodev.screenmatch.model.DadosSerie;
import br.com.jfabiodev.screenmatch.model.DadosTemporadas;
import br.com.jfabiodev.screenmatch.service.ConsumoAPI;
import br.com.jfabiodev.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
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

//        for (int i = 0; i < dadosSerie.totalTemporadas() ; i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size() ; j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t ->t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}

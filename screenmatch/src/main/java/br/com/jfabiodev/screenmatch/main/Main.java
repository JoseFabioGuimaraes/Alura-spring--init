package br.com.jfabiodev.screenmatch.main;

import br.com.jfabiodev.screenmatch.service.ConsumoAPI;

import java.util.Scanner;

public class Main {
    final Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f9173cff";

    public void exibeMenu(){
        System.out.println("Digite o nome da Série: ");
        String nomeSerie = scanner.nextLine();
        var consumoAPI = new ConsumoAPI();
        String json = consumo.obterDados(ENDERECO+ nomeSerie.replace(" ", "+") +API_KEY);

    }
}

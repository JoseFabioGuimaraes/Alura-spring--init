package br.com.jfabiodev.TabelaFIPEAPI.main;

import br.com.jfabiodev.TabelaFIPEAPI.model.Dados;
import br.com.jfabiodev.TabelaFIPEAPI.model.Modelos;
import br.com.jfabiodev.TabelaFIPEAPI.service.ConsomeAPI;
import br.com.jfabiodev.TabelaFIPEAPI.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final ConsomeAPI consumoAPI = new ConsomeAPI();
    private final ConverteDados conversorDados = new ConverteDados();

    public void exibeMenu() {
        var menu = """
                ************OPÇÕES************
                CARRO
                MOTO
                CAMINHÃO

                DIGITE UMA DAS OPÇÕESDE CONSULTA:
                """;

        System.out.println(menu);
        var opcao = scanner.nextLine();
        String endereco = "";

        String URLBase = "https://parallelum.com.br/fipe/api/v1";
        if (opcao.toUpperCase().contains("CARR")) {
            endereco = URLBase + "/carros/marcas";
        } else if (opcao.toUpperCase().contains("MOT")) {
            endereco = URLBase + "/motos/marcas";
        } else {
            endereco = URLBase + "/caminhoes/marcas";
        }

        System.out.println(endereco);
        var json = consumoAPI.obterDados(endereco);

        System.out.println(json);

        var marcas = conversorDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código da marca que deseja consultar: ");
        var codigoMarca = scanner.nextLine();

        endereco = endereco+"/" + codigoMarca + "/modelos";
        json = consumoAPI.obterDados(endereco);
        var modeloLista = conversorDados.obterDados(json, Modelos.class);
        System.out.println("Modelos dessa marca: ");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }
}

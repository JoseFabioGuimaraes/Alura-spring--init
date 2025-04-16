package br.com.jfabiodev.TabelaFIPEAPI.main;

import br.com.jfabiodev.TabelaFIPEAPI.model.Dados;
import br.com.jfabiodev.TabelaFIPEAPI.model.Modelos;
import br.com.jfabiodev.TabelaFIPEAPI.model.Veiculo;
import br.com.jfabiodev.TabelaFIPEAPI.service.ConsomeAPI;
import br.com.jfabiodev.TabelaFIPEAPI.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        System.out.println("Digite um trecho do nome do carro a ser buscado: ");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toUpperCase().contains(nomeVeiculo.toUpperCase()))
                .collect(Collectors.toList());

        System.out.println("Modelos Filtrados: \n");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo desejado: ");
        var codVeiculo = scanner.nextLine();

        endereco = endereco+ "/"+codVeiculo + "/anos";
        json = consumoAPI.obterDados(endereco);

        List<Dados> anos = conversorDados.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (Dados ano : anos) {
            var enderecoAnos = endereco + "/" + ano.codigo();
            json = consumoAPI.obterDados(enderecoAnos);
            Veiculo veiculo = conversorDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Todos os veículos filtrados avaliação por anos: ");
        veiculos.forEach(System.out::println);
    }
}

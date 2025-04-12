package Atividade4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        List<Produto> eletronicos = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase("eletrônicos")
                && p.getPreco()<1000.00)
                .collect(Collectors.toList());

        eletronicos.forEach(System.out::println);

        Map<String, List<Produto>> produtoPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println(produtoPorCategoria);

        Map<String, Long> quantidadePorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
        System.out.println(quantidadePorCategoria);

        Map<String, Optional<Produto>> produtoMaisCaro = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));
        System.out.println(produtoMaisCaro);

        Map<String, Double> somaPrecoPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco)));

        System.out.println(somaPrecoPorCategoria);



    }
}
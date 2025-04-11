package Atividade3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Alice", 22),
                new Pessoa("Bob", 17),
                new Pessoa("Charlie", 19)
        );

        pessoas.stream().filter(p -> p.idade > 18)
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(pessoa -> System.out.println(pessoa.getNome() + " tem : " + pessoa.idade));

        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        List<Produto> eletronicos = produtos.stream()
                .filter(produto -> produto.getCategoria().equalsIgnoreCase("eletrônicos")
                        && produto.getPreco()<1000)
                .sorted(Comparator.comparing(Produto::getPreco))
                .collect(Collectors.toList());

        eletronicos.stream()
                .limit(2)
                .forEach(System.out::println);

    }
}

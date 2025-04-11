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
                .forEach(pessoa -> System.out.println(pessoa.getNome() + "tem : " + pessoa.idade));

    }
}

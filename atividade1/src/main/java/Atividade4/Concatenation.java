package Atividade4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Concatenation {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");

        String resultado = String.join(", ", nomes);
        System.out.println(resultado);
    }
}

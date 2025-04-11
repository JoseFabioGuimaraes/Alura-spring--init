package Atividade3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListsOddStream {
    public static void main(String[] args) {
        List<List<Integer>> listaDeNumeros = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );
        List<Integer> listaDePrimos = listaDeNumeros.stream()
                .flatMap(List::stream)
                .filter(ListsOddStream::Primo)
                .sorted()
                .collect(Collectors.toList());

        listaDePrimos.forEach(System.out::println);
    }

    private static boolean Primo(int numero){
        if(numero<= 1) return false;
        for(int i = 2; i<=Math.sqrt(numero);i++){
            if(numero % i == 0) return false;
        }
        return true;
    }
}

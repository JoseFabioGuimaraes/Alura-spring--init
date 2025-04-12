package Atividade4;

import java.util.Arrays;
import java.util.List;

public class EvenOdd {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n%2 == 0)
                .toList();

        List<Integer> numerosImpares = numeros.stream()
                .filter(n -> n%2 != 0)
                .toList();

        System.out.println(numerosPares + "\n" + numerosImpares);
    }
}

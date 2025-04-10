package Atividade3;

import java.util.Arrays;
import java.util.List;

public class OddStream {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);
        numeros.stream().filter(n -> !(n % 2 ==0))
                .map(n -> n * 2)
                .forEach(System.out::println);
    }
}

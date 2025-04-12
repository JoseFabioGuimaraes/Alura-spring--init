package Atividade4;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MaxList {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> stats = numeros.stream()
                .max(Integer::compare);
        stats.ifPresent(System.out::println);

    }
}

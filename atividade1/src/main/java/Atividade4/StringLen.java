package Atividade4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringLen {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> stringLen = palavras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(stringLen);

    }
}

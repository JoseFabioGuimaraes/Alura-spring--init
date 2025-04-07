package Atividade2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Multiplicacao multiplicacao= (a,b) -> a*b;
        System.out.println("A multiplicação é: " + multiplicacao.multiplicacao(2,2));
        //2
        Primo primo = a -> {
            if (a <= 1) return false;
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) return false;
            }
            return true;
        };

        System.out.println(primo.primo(7));

        //3
        Transformador transformador = s -> s.toUpperCase();

        System.out.println(transformador.transformar("teste"));

        //4
        Palindromo palindromo = palavra -> {
            String invertida = new StringBuilder(palavra).reverse().toString();
            if( palavra.equalsIgnoreCase(invertida)) return true;
            else return false;
        };
        System.out.println(palindromo.palindromo("natan" ));

        //5

        List<Integer> numero = Arrays.asList(3,5,10);
        numero.replaceAll(n -> n*3);
        System.out.println(numero);

        //6
        List<String> nomes = Arrays.asList("Fabio","Guimaraes","Filho");
        nomes.sort((a,b) -> a.compareTo(b));
        System.out.println(nomes);

        //7
        Divisao divisao = (a, b) ->{
            if (b== 0){
                throw new ArithmeticException("Divisão por 0");
            } else return a/b;

        };

        try {
            System.out.println(divisao.divisao(4, 2));
            System.out.println(divisao.divisao(4, 0));
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
    }
}

package br.com.atividade1_alura.atividade1;

import br.com.atividade1_alura.atividade1.service.Contador;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Atividade1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Atividade1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o número");
		int numero = scanner.nextInt();
		Contador contador = new Contador(numero);
	}

	/*
	Exercicio 2:
		Acessamos o https://mvnrepository.com/, procuramos a dependencia Gson e copiamos o
		código da dependencia. Acessamos o pom.xml e colamos o código dentro da tag <dependencies>
	*/



}

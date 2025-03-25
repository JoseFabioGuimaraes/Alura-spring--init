package br.com.atividade1_alura.atividade1;

import br.com.atividade1_alura.atividade1.model.Tarefa;
import br.com.atividade1_alura.atividade1.service.Contador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class Atividade1Application implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(Atividade1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Tarefa tarefaLida = objectMapper.readValue(new File("tarefa.json"), Tarefa.class);
		System.out.println("Tarefa lida do JSON:");
		System.out.println(tarefaLida);
	}


	/*
	@Override
	public void run(String... args) throws Exception {
		Tarefa tarefa = new Tarefa("Assistir aula 1", false, "Fábio");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("tarefa.json"),tarefa);
		System.out.println("Arquivo salvo");
	}*/


	/*@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o número");
		int numero = scanner.nextInt();
		Contador contador = new Contador(numero);
	}
	*/

	/*
	Exercicio 2:
		Acessamos o https://mvnrepository.com/, procuramos a dependencia Gson e copiamos o
		código da dependencia. Acessamos o pom.xml e colamos o código dentro da tag <dependencies>
	*/



}

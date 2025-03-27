package br.com.jfabiodev.screenmatch;

import br.com.jfabiodev.screenmatch.model.DadosEpisodio;
import br.com.jfabiodev.screenmatch.model.DadosSerie;
import br.com.jfabiodev.screenmatch.model.DadosTemporadas;
import br.com.jfabiodev.screenmatch.service.ConsumoAPI;
import br.com.jfabiodev.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		String json = consumoAPI.obterDados("https://www.omdbapi.com/?t=mr.robot&apikey=f9173cff");
		System.out.println(json);

		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obterDados(json,DadosSerie.class);
		System.out.println(dadosSerie);

		json = consumoAPI.obterDados("https://www.omdbapi.com/?t=mr.robot&season=1&episode=2&apikey=f9173cff");
		DadosEpisodio episodio = converteDados.obterDados(json,DadosEpisodio.class);
		System.out.println(episodio);
		List<DadosTemporadas> temporadas = new ArrayList<>();

		for (int i = 1; i<= dadosSerie.totalTemporadas(); i++){
			json = consumoAPI.obterDados("https://www.omdbapi.com/?t=mr.robot&season="+i+"&apikey=f9173cff");
			DadosTemporadas temporada = converteDados.obterDados(json, DadosTemporadas.class);
			temporadas.add(temporada);
		}
		temporadas.forEach(System.out::println);
	}
}

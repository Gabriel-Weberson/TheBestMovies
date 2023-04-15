package br.com.top250filmes.thebestmovies;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Realiza a conexão HTTP e busca os 250 melhores filmes
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// Extrai dados da API no formato Json
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// Exibe e manipula os dados
		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println("Título: " + filme.get("title"));
			System.out.println("Poster: " + filme.get("image"));
			System.out.println("Classificação: " + filme.get("imDbRating"));
			System.out.println();
		}
	}
}

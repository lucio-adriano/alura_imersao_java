import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os filmes
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.mocki.io/v2/549a5d8b"))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, poster, classificação)
        JsonParser parse = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parse.parse(body);

        //exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {
            Float classificacao = Float.valueOf(filme.get("imDbRating"));
            Integer estrela =  Math.round(classificacao);
            System.out.println("\u001b[1mTitulo:\u001b[m " + "\u001b[3m"+filme.get("title"));
            System.out.println("\u001b[1mPoster:\u001b[m " + "\u001b[3m"+filme.get("image"));
            System.out.println("\u001b[1m\u001b[30m\u001b[43mClassificção:\u001b[m " + classificacao);
            for (var i = 0; i < estrela; i++){
                System.out.print("\u001b[33m* \u001b[m");
            }

            System.out.println();

        }
    }
}

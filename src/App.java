import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
            .uri(URI.create("https://api.mocki.io/v2/549a5d8b/Top250Movies"))
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, poster, classificação)
        JsonParser parse = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parse.parse(body);

        //exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {

            var titulo = filme.get("title");
            var imagem = filme.get("image");
            String nomeArquivo = titulo.replace(":", "-")  + ".png";

            try {
                InputStream inputStream = new URL(imagem).openStream();
                System.out.println("Gerando imagem - [" + titulo + "]");
                geradora.cria(inputStream, nomeArquivo);

            } catch (final java.io.FileNotFoundException e) {
                System.out.println("Imagem não encontrada ou link inválido");
            }
        }
    }
}

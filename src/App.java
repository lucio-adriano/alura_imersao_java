import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os filmes
        String url ="https://api.mocki.io/v2/549a5d8b/Top250Movies";
        ExtratorConteudo extrator = new ExtratorConteudoIMDB();
  //      String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
  //      ExtratorConteudo extrator = new ExtratorConteudoNasa();

        var  http = new ClienteHttp();
        String json = http.buscaDados(url);
        
          //exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (Conteudo conteudo : conteudos) {
            System.out.println(conteudo.getUrlImagem());
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            System.out.println("Gerando imagem - [" + conteudo.getTitulo() + "]");
            geradora.cria(inputStream, conteudo.getTitulo().replace(":", "-")  + ".png");
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo {
    public List<Conteudo> extraiConteudos (String json){

        // extrair os dados que interessam (titulo, poster, classificação)
        JsonParser parse = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parse.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();
        //exibir e manipular os dados
        for (Map<String, String> atributo : listaDeAtributos) {
            String titulo = atributo.get("title");
            String urlImagem = atributo.get("url");
            var conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}

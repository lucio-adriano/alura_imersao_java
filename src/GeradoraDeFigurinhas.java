import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{

        // leitura da imagem
       // InputStream inputStream = new URL(url).openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em mémoria com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copia nova imagem original para o nova imagem (em memória)
        Graphics2D graphics = (Graphics2D)novImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // escreve uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);

        //escreve a nova imagem em um arquivo
        ImageIO.write(novImagem, "png", new File("saida/"+nomeArquivo));

    }

}

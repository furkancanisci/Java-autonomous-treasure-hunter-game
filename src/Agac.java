import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Agac extends Engel {
    private static final String AGAC_RESIM_YOLU = "res/agac.png";
    private Image agacResmi;

    public Agac(Lokasyon lokasyon, int boyutX, int boyutY) {
        super(lokasyon, boyutX, boyutY);

        // Ağaç resmini yükle
        try {
            agacResmi = ImageIO.read(new File(AGAC_RESIM_YOLU));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ciz(Graphics g, int kareBoyutu) {
        // Ağaç resmini çiz
        g.drawImage(agacResmi, lokasyon.getX() * kareBoyutu, lokasyon.getY() * kareBoyutu,
                boyutX * kareBoyutu, boyutY * kareBoyutu, null);
    }

    @Override
    public void etki(Karakter karakter) {
        // Ağacın karaktere etkisi
    }
}

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Kaya extends Engel {
    private static final String KAYA_RESIM_YOLU = "res/kaya.png";
    private Image kayaResmi;

    public Kaya(Lokasyon lokasyon) {
        super(lokasyon, 1, 1);

        // Kaya resmini yükle
        try {
            kayaResmi = ImageIO.read(new File(KAYA_RESIM_YOLU));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ciz(Graphics g, int kareBoyutu) {
        // Kaya resmini çiz
        g.drawImage(kayaResmi, lokasyon.getX() * kareBoyutu, lokasyon.getY() * kareBoyutu, kareBoyutu, kareBoyutu, null);
    }

    @Override
    public void etki(Karakter karakter) {
        // Kaya'nın karaktere etkisi
    }
}

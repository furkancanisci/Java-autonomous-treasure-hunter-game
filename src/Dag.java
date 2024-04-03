import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Dag extends Engel {
    private static final String DAG_RESIM_YAZ = "res/dagYaz.png";
    private static final String DAG_RESIM_KIS = "res/dagKis.png";
    private Image dagResmiYaz;
    private Image dagResmiKis;

    public Dag(Lokasyon lokasyon, int boyutX, int boyutY) {
        super(lokasyon, boyutX, boyutY);

        // Resimleri yükle
        try {
            dagResmiYaz = ImageIO.read(new File(DAG_RESIM_YAZ));
            dagResmiKis = ImageIO.read(new File(DAG_RESIM_KIS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ciz(Graphics g, int kareBoyutu) {
        // Mevsime göre doğru resmi çiz
        if (lokasyon.getX() < KareselHarita.DUVAR_BOYUT_X * KareselHarita.DUVAR_BOYUT_X / 2) {
            g.drawImage(dagResmiYaz, lokasyon.getX() * kareBoyutu, lokasyon.getY() * kareBoyutu,
                    boyutX * kareBoyutu, boyutY * kareBoyutu, null);
        } else {
            g.drawImage(dagResmiKis, lokasyon.getX() * kareBoyutu, lokasyon.getY() * kareBoyutu,
                    boyutX * kareBoyutu, boyutY * kareBoyutu, null);
        }
    }

    @Override
    public void etki(Karakter karakter) {
        // Dağın karaktere etkisi
    }
}

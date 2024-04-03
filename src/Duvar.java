import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Duvar extends Engel {
    private Image duvarResmi;

    public Duvar(Lokasyon lokasyon, int boyutX, int boyutY) {
        super(lokasyon, boyutX, boyutY);

        // Duvar resmini yükle
        try {
            duvarResmi = ImageIO.read(new File("res/duvar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ciz(Graphics g, int kareBoyutu) {
        // Duvarı çiz
        g.drawImage(duvarResmi, lokasyon.getX() * kareBoyutu, lokasyon.getY() * kareBoyutu,
                boyutX * kareBoyutu, boyutY * kareBoyutu, null);
    }

    @Override
    public void etki(Karakter karakter) {
        // Duvarın karaktere etkisi
    }
}

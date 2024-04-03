import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Karakter {
    private int x;
    private int y;
    private Image karakterResmi;
    private int hizX;
    private int hizY;

    public Karakter(int x, int y) {
        this.x = x;
        this.y = y;
        this.hizX = 0;
        this.hizY = 0;

        // Karakter resmini yükle
        try {
            karakterResmi = ImageIO.read(new File("res/karakter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Getter ve setter metodları
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHizX() {
        return hizX;
    }

    public void setHizX(int hizX) {
        this.hizX = hizX;
    }

    public int getHizY() {
        return hizY;
    }

    public void setHizY(int hizY) {
        this.hizY = hizY;
    }


    public void hareketEt(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Karakterin resmini çizmek için
    public void draw(Graphics g, int kareBoyutu) {
        g.drawImage(karakterResmi, x * kareBoyutu, y * kareBoyutu, kareBoyutu, kareBoyutu, null);
    }
}

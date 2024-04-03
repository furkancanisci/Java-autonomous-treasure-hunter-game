import java.awt.*;

public abstract class Engel {
    protected Lokasyon lokasyon; // Engelın konumu
    protected int boyutX; // Engelın boyutları
    protected int boyutY;

    public Engel(Lokasyon lokasyon, int boyutX, int boyutY) {
        this.lokasyon = lokasyon;
        this.boyutX = boyutX;
        this.boyutY = boyutY;
    }

    // Engelı çizmek için soyut metot
    public abstract void ciz(Graphics g, int kareBoyutu);

    // Engelın karaktere etkisi için soyut metot
    public abstract void etki(Karakter karakter);
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HaritaPanel extends JPanel implements Runnable, KeyListener {
    private KareselHarita harita;
    private Karakter karakter;
    private static final Color YAZ_TEMASI_RENK = new Color(255, 234, 171);
    private boolean running;
    private Thread gameThread;
    private static final int FPS = 60;
    private static final long FRAME_INTERVAL = 1000 / FPS;

    public HaritaPanel(KareselHarita harita) {
        this.harita = harita;
        this.karakter = new Karakter(0, 0); // Karakteri başlangıç noktasına yerleştir
        setPreferredSize(new Dimension(800, 800)); // Panel boyutunu ayarla
        addKeyListener(this); // Klavye olaylarını dinle
        setFocusable(true); // Klavye olaylarını almak için panelin odaklanmasını sağla
    }

    public void startGame() {
        if (gameThread == null) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stopGame() {
        running = false;
        if (gameThread != null) {
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        long startTime;
        long elapsedTime;
        long sleepTime;

        while (running) {
            startTime = System.currentTimeMillis();

            update();
            repaint();

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = FRAME_INTERVAL - elapsedTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void update() {
        int karakterX = karakter.getX();
        int karakterY = karakter.getY();

        // Karakterin hareketini kontrol etmek için yeni konumları hesapla
        int yeniX = karakterX + karakter.getHizX(); // Karakterin x yönündeki hızıyla yeni x konumu
        int yeniY = karakterY + karakter.getHizY(); // Karakterin y yönündeki hızıyla yeni y konumu

        // Yeni konum harita sınırlarını aşıyorsa, hareketi iptal et
        if (yeniX < 0 || yeniX >= harita.getBoyutX() || yeniY < 0 || yeniY >= harita.getBoyutY()) {
            return;
        }

        // Yeni konumda duvar veya engel varsa, hareketi iptal et
        if (harita.getHarita()[yeniX][yeniY] == KareselHarita.DUVAR ||
                harita.getHarita()[yeniX][yeniY] == KareselHarita.DAG ||
                harita.getHarita()[yeniX][yeniY] == KareselHarita.KAYA ||
                harita.getHarita()[yeniX][yeniY] == KareselHarita.AGAC) {
            return;
        }

        // Yeni konumda sandık varsa, topla
        if (harita.getHarita()[yeniX][yeniY] == KareselHarita.SANDIK) {
            JOptionPane.showMessageDialog(this, "Sandık toplandı!");
            harita.getHarita()[yeniX][yeniY] = 0; // Sandığı haritadan kaldır
        }

        // Karakterin yeni konumunu ayarla
        karakter.setX(yeniX);
        karakter.setY(yeniY);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Kare boyutunu hesapla
        int kareBoyutu = Math.min(getWidth() / harita.getBoyutX(), getHeight() / harita.getBoyutY());

        cizHaritayi(g, kareBoyutu);
        karakter.draw(g, kareBoyutu); // Karakteri çiz
    }

    private void cizHaritayi(Graphics g, int kareBoyutu) {
        int[][] haritaMatrisi = harita.getHarita();

        for (int i = 0; i < harita.getBoyutX(); i++) {
            for (int j = 0; j < harita.getBoyutY(); j++) {
                // Sol tarafı yaz, sağ tarafı kış yap
                if (i < harita.getBoyutX() / 2) {
                    g.setColor(YAZ_TEMASI_RENK);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(i * kareBoyutu, j * kareBoyutu, kareBoyutu, kareBoyutu);

                // Kareleri grid olarak çizmek için:
                g.setColor(Color.BLACK);
                g.drawRect(i * kareBoyutu, j * kareBoyutu, kareBoyutu, kareBoyutu);

                // Engelleri ve sandıkları çiz
                switch (haritaMatrisi[i][j]) {
                    case KareselHarita.DUVAR:
                        // Duvarın boyutunu kontrol et
                        if (i + KareselHarita.DUVAR_BOYUT_X <= harita.getBoyutX()) {
                            Engel duvar = new Duvar(new Lokasyon(i, j), KareselHarita.DUVAR_BOYUT_X, KareselHarita.DUVAR_BOYUT_Y);
                            duvar.ciz(g, kareBoyutu);
                        }
                        break;
                    case KareselHarita.DAG:
                        // Dağ nesnesinin boyutunu kontrol et
                        if (i + 15 <= harita.getBoyutX() && j + 15 <= harita.getBoyutY()) {
                            Engel dag = new Dag(new Lokasyon(i, j), 15, 15); // Dağ boyutu burada değiştirilebilir
                            dag.ciz(g, kareBoyutu);
                        }
                        break;
                    case KareselHarita.KAYA:
                        // Kaya nesnesinin boyutunu kontrol et
                        Engel kaya = new Kaya(new Lokasyon(i, j));
                        kaya.ciz(g, kareBoyutu);
                        break;
                    case KareselHarita.AGAC:
                        // Ağaç nesnesinin boyutunu kontrol et
                        Engel agac = new Agac(new Lokasyon(i, j), 1, 1); // Ağaç boyutu burada değiştirilebilir
                        agac.ciz(g, kareBoyutu);
                        break;

                    case KareselHarita.SANDIK:
                        g.setColor(Color.ORANGE);
                        g.fillRect(i * kareBoyutu, j * kareBoyutu, kareBoyutu, kareBoyutu);
                        break;
                }
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
                karakter.hareketEt(0, -1);
                break;
            case KeyEvent.VK_S:
                karakter.hareketEt(0, 1);
                break;
            case KeyEvent.VK_A:
                karakter.hareketEt(-1, 0);
                break;
            case KeyEvent.VK_D:
                karakter.hareketEt(1, 0);
                break;
        }

        toplaSandik(); // Sandığı topla

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private void toplaSandik() {
        int karakterX = karakter.getX();
        int karakterY = karakter.getY();

        if (harita.getHarita()[karakterX][karakterY] == KareselHarita.SANDIK) {
            // Karakter sandığı topladı
            JOptionPane.showMessageDialog(this, "Sandık toplandı!");
            harita.getHarita()[karakterX][karakterY] = 0; // Sandığı haritadan kaldır
        }
    }
}

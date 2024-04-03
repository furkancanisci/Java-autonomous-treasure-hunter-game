import javax.swing.*;

public class AnaPencere extends JFrame {
    public AnaPencere() {
        setTitle("Hazine Avcısı");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // Haritayı oluştur
        KareselHarita harita = new KareselHarita(100, 100);
        harita.haritayiOlustur();

        // Harita panelini oluştur ve pencereye ekle
        HaritaPanel haritaPanel = new HaritaPanel(harita);
        add(haritaPanel);

        // Pencereyi görünür yap
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AnaPencere::new);
    }
}

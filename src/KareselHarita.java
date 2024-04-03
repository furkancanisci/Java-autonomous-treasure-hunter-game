import java.util.Random;

public class KareselHarita {
    private int[][] harita;
    private int boyutX;
    private int boyutY;
    public static final int DUVAR = 1;
    public static final int SANDIK = 2;
    public static final int ALTIN = 3;
    public static final int GUMUS = 4;
    public static final int BRONZ = 5;
    public static final int DUVAR_BOYUT_X = 10;
    public static final int DUVAR_BOYUT_Y = 1;
    public static final int KAYA = 6;
    public static final int AGAC = 7;
    public static final int DAG = 8;

    public KareselHarita(int boyutX, int boyutY) {
        this.boyutX = boyutX;
        this.boyutY = boyutY;
        this.harita = new int[boyutX][boyutY];
    }

    public void haritayiOlustur() {
        Random rnd = new Random();

// Dağları yerleştir
        for (int i = 0; i < 2; i++) { // Toplam 2 dağ olacak
            int x = i == 0 ? rnd.nextInt(boyutX / 2) : boyutX / 2 + rnd.nextInt(boyutX / 2); // Sol veya sağ tarafta
            int y = rnd.nextInt(boyutY - 15); // Dağın y koordinatı

            // Eğer rastgele seçilen konum uygunsa dağı yerleştir
            boolean uygunKonum = true;
            for (int j = x; j < x + 15; j++) {
                for (int k = y; k < y + 15; k++) {
                    if (harita[j][k] != 0) { // Eğer bir engel varsa, uygun değil
                        uygunKonum = false;
                        break;
                    }
                }
            }

            if (uygunKonum) {
                for (int j = x; j < x + 15; j++) {
                    for (int k = y; k < y + 15; k++) {
                        harita[j][k] = DAG;
                    }
                }
            } else {
                // Yeniden rastgele konum seç
                i--;
            }
        }

        // Diğer engelleri yerleştir
        for (int i = 0; i < 8; i++) { // Toplam 8 duvar olacak
            int x = rnd.nextInt(boyutX - DUVAR_BOYUT_X + 1); // Duvarın x koordinatı
            int y = rnd.nextInt(boyutY); // Duvarın y koordinatı

            // Duvarın boyutu kadar alanı kontrol et, eğer uygunsa duvarı yerleştir
            boolean uygunKonum = true;
            for (int j = x; j < x + DUVAR_BOYUT_X; j++) {
                if (harita[j][y] != 0) { // Eğer bir engel varsa, uygun değil
                    uygunKonum = false;
                    break;
                }
            }

            // Uygun konumdaysa duvarı yerleştir
            if (uygunKonum) {
                for (int j = x; j < x + DUVAR_BOYUT_X; j++) {
                    harita[j][y] = DUVAR;
                }
            } else {
                // Yeniden rastgele konum seç
                i--;
            }
        }

        // Kayaları ve ağaçları yerleştir
        for (int i = 0; i < 20; i++) { // Toplam 20 kaya olacak
            int boyut = rnd.nextBoolean() ? 2 : 3; // 2x2 veya 3x3 boyutta kaya
            int x = rnd.nextInt(boyutX - boyut + 1); // Kaya'nın x koordinatı
            int y = rnd.nextInt(boyutY - boyut + 1); // Kaya'nın y koordinatı

            // Eğer rastgele seçilen konum uygunsa kaya yerleştir
            boolean uygunKonum = true;
            for (int j = x; j < x + boyut; j++) {
                for (int k = y; k < y + boyut; k++) {
                    if (harita[j][k] != 0) { // Eğer bir engel varsa, uygun değil
                        uygunKonum = false;
                        break;
                    }
                }
            }

            if (uygunKonum) {
                for (int j = x; j < x + boyut; j++) {
                    for (int k = y; k < y + boyut; k++) {
                        harita[j][k] = KAYA;
                    }
                }
            } else {
                // Yeniden rastgele konum seç
                i--;
            }
        }

        for (int i = 0; i < 15; i++) { // Toplam 15 ağaç olacak
            int boyut = rnd.nextInt(4) + 2; // Rastgele 2x2 ile 5x5 boyutunda ağaç
            int x = rnd.nextInt(boyutX - boyut + 1); // Ağacın x koordinatı
            int y = rnd.nextInt(boyutY - boyut + 1); // Ağacın y koordinatı

            // Eğer rastgele seçilen konum uygunsa ağaç yerleştir
            boolean uygunKonum = true;
            for (int j = x; j < x + boyut; j++) {
                for (int k = y; k < y + boyut; k++) {
                    if (harita[j][k] != 0) { // Eğer bir engel varsa, uygun değil
                        uygunKonum = false;
                        break;
                    }
                }
            }

            if (uygunKonum) {
                for (int j = x; j < x + boyut; j++) {
                    for (int k = y; k < y + boyut; k++) {
                        harita[j][k] = AGAC;
                    }
                }
            } else {
                // Yeniden rastgele konum seç
                i--;
            }
        }
    }

    public int[][] getHarita() {
        return harita;
    }

    public int getBoyutX() {
        return boyutX;
    }

    public int getBoyutY() {
        return boyutY;
    }
}

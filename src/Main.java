public class Main {
    public static void main(String[] args) {
        // Harita boyutunu belirle
        int boyutX = 10;
        int boyutY = 10;

        // Haritayı oluştur
        KareselHarita harita = new KareselHarita(boyutX, boyutY);
        harita.haritayiOlustur();

        // Oluşturulan haritayı yazdır
        int[][] olusturulanHarita = harita.getHarita();
        for (int i = 0; i < boyutX; i++) {
            for (int j = 0; j < boyutY; j++) {
                System.out.print(olusturulanHarita[i][j] + " ");
            }
            System.out.println();
        }
    }
}
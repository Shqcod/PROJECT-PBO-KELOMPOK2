import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private int imageWidth;  // Lebar gambar yang diinginkan
    private int imageHeight; // Tinggi gambar yang diinginkan
    private int imageX;      // Posisi X gambar
    private int imageY;      // Posisi Y gambar

    public BackgroundPanel(String imagePath) {
        // Memuat gambar dari path yang diberikan
        backgroundImage = new ImageIcon(imagePath).getImage();

        // Default ukuran gambar dan posisi
        imageWidth = -1; // -1 berarti gunakan ukuran panel
        imageHeight = -1; // -1 berarti gunakan ukuran panel
        imageX = 0;       // Default ke kiri atas
        imageY = 0;       // Default ke kiri atas
    }

    // Method untuk mengatur ukuran gambar
    public void setImageSize(int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
        repaint(); // Meminta panel untuk menggambar ulang
    }

    // Method untuk mengatur posisi gambar
    public void setImagePosition(int x, int y) {
        this.imageX = x;
        this.imageY = y;
        repaint(); // Meminta panel untuk menggambar ulang
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Menggambar gambar di latar belakang dengan ukuran tertentu
        if (backgroundImage != null) {
            int width = (imageWidth > 0) ? imageWidth : getWidth();   // Gunakan ukuran yang ditentukan atau ukuran panel
            int height = (imageHeight > 0) ? imageHeight : getHeight();

            g.drawImage(backgroundImage, imageX, imageY, width, height, this);
        }
    }
}

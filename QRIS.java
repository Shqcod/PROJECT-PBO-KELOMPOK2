import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id);
    }

     // Implementasi method abstract dari class Pembayaran
    @Override
    public String getTipePembayaran() {
        return "QRIS";
    }

    public static void tampilkanQRCode() {
        // Jalur file QR code (pastikan file ini tersedia di jalur yang sesuai)
        String qrCodePath = "assets//QRIS.png";
    
        // Panel untuk menampilkan QR code
        ImageIcon qrImageIcon = new ImageIcon(qrCodePath);

        // Mengubah ukuran gambar sesuai kebutuhan
        Image qrImage = qrImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // ukuran baru 200x200
        qrImageIcon = new ImageIcon(qrImage);
        
        // Menampilkan gambar QR Code
        JLabel qrLabel = new JLabel(qrImageIcon);
        JPanel qrPanel = new JPanel();
        qrLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrPanel.add(qrLabel, BorderLayout.CENTER);
    
        // Tampilkan QR code dalam JOptionPane
        JOptionPane.showMessageDialog(
                null,
                qrPanel,
                "Scan QR Code untuk Membayar",
                JOptionPane.PLAIN_MESSAGE
        );
    }

}
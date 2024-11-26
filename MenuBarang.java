import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuBarang {
    //private BarangTablePanel barangTablePanel
    public static class MenuTambahBarang extends JDialog {
        private List<Barang> listBarang;
        public MenuTambahBarang(Frame owner, BarangTablePanel barangTablePanel, List<Barang> listBarang) {
            super(owner, "Tambah Barang", true);
            setLayout(new GridLayout(5, 2, 10, 10));
            setSize(400, 300);

            add(new JLabel("ID"));
            JTextField fieldId = new JTextField();
            add(fieldId);

            add(new JLabel("Nama Barang:"));
            JTextField fieldNama = new JTextField();
            add(fieldNama);

            add(new JLabel("Kategori:"));
            JTextField fieldKategori = new JTextField();
            add(fieldKategori);

            add(new JLabel("Harga:"));
            JTextField fieldHarga = new JTextField();
            add(fieldHarga);

            add(new JLabel("Stok:"));
            JTextField fieldStok = new JTextField();
            add(fieldStok);

            JButton btnTambah = new JButton("Tambah");
            btnTambah.addActionListener(e -> {
                try { // Tambahkan barang ke tabel
                    int id = Integer.parseInt(fieldId.getText());
                    String nama = fieldNama.getText();
                    String kategori = fieldKategori.getText();
                    double harga = Double.parseDouble(fieldHarga.getText());
                    int stok = Integer.parseInt(fieldStok.getText());
    
                    Barang barang = new Barang (id, nama, harga, stok, kategori);
                    ListBarang.tambahBarang("barang.txt", listBarang, barang);
                    barangTablePanel.tambahBarang(barang);
    
                    JOptionPane.showMessageDialog(owner, "Barang berhasil ditambahkan");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "Input tidak valid");
                }
               
            });
            add(btnTambah);

            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());
            add(btnBatal);
        }
    }

    public static class MenuHapusBarang extends JDialog {
        public MenuHapusBarang(Frame owner, DefaultTableModel tabelModel) {
            super(owner, "Hapus Barang", true);
            setLayout(new GridLayout(2, 2, 10, 10));
            setSize(300, 150);

            add(new JLabel("ID Barang:"));
            JTextField fieldId = new JTextField();
            add(fieldId);

            JButton btnHapus = new JButton("Hapus");
            btnHapus.addActionListener(e -> {
                int id = Integer.parseInt(fieldId.getText());
                int row = -1;

                for (int i = 0; i < tabelModel.getRowCount(); i++) {
                    if ((int) tabelModel.getValueAt(i, 0) == id) {
                        row = i;
                        break;
                    }
                }

                if (row != -1) {
                    tabelModel.removeRow(row);
                }

                dispose();
            });
            add(btnHapus);

            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());
            add(btnBatal);
        }
    }
}
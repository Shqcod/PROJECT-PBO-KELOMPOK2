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
            setLocationRelativeTo(null);

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
        private List<Barang> listBarang;
        
        public MenuHapusBarang(Frame owner, BarangTablePanel barangTablePanel, List<Barang> listBarang) {
            super(owner, "Hapus Barang", true);
            setLayout(new GridLayout(2, 2, 10, 10));
            setSize(300, 150);
            setLocationRelativeTo(null);  

            add(new JLabel("ID Barang:"));
            JTextField fieldId = new JTextField();
            add(fieldId);

            JButton btnHapus = new JButton("Hapus");
            btnHapus.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(fieldId.getText());
                    Barang barangDihapus = null;

                    for (Barang barang : listBarang){
                        if (barang.getId() == id){
                            barangDihapus = barang;
                            break;
                        }
                    }

                    if (barangDihapus != null) {
                        listBarang.remove(barangDihapus);

                        ListBarang.saveBarangToFile("barang.txt", listBarang);

                        // Hapus baris dari tabel model
                    for (int i = 0; i < barangTablePanel.getRowCount(); i++) {
                        if ((int) barangTablePanel.getValueAt(i, 0) == id) {
                            barangTablePanel.removeRow(i);
                            break;
                        }
                    }

                    JOptionPane.showMessageDialog(owner, "Barang berhasil dihapus!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(owner, "Barang dengan ID " + id + " tidak ditemukan.");
                }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "ID barang tidak valid");
                }
               
            });
            add(btnHapus);

            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());
            add(btnBatal);
        }
    }

    public static class MenuEditBarang extends JDialog {
        private List<Barang> listBarang;
    
        public MenuEditBarang(Frame owner, BarangTablePanel barangTablePanel, List<Barang> listBarang) {
            super(owner, "Edit Barang", true);
            this.listBarang = listBarang;
            setLayout(new GridLayout(6, 2, 10, 10));
            setSize(400, 350);
            setLocationRelativeTo(null);  
    
            // Menambahkan Label dan Field ID Barang
            add(new JLabel("Masukkan ID Barang:"));
            JTextField fieldId = new JTextField();
            add(fieldId);
    
            JButton btnCari = new JButton("Cari");
            add(btnCari);
    
            // Menambahkan Label dan Field untuk data barang
            add(new JLabel("Harga Baru:"));
            JTextField fieldHarga = new JTextField();
            add(fieldHarga);
    
            add(new JLabel("Stok Baru:"));
            JTextField fieldStok = new JTextField();
            add(fieldStok);
    
            // Tombol Edit untuk mengonfirmasi perubahan
            JButton btnEdit = new JButton("Edit");
            btnEdit.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(fieldId.getText());
                    double hargaBaru = Double.parseDouble(fieldHarga.getText());
                    int stokBaru = Integer.parseInt(fieldStok.getText());
    
                    Barang barangDiedit = null;
    
                    // Cari barang berdasarkan ID
                    for (Barang barang : listBarang) {
                        if (barang.getId() == id) {
                            barangDiedit = barang;
                            break;
                        }
                    }
    
                    if (barangDiedit != null) {
                        // Perbarui harga dan stok
                        barangDiedit.setHarga(hargaBaru);
                        barangDiedit.setStok(stokBaru);
    
                        // Simpan perubahan ke file
                        ListBarang.saveBarangToFile("barang.txt", listBarang);
    
                        // Perbarui tabel model
                        for (int i = 0; i < barangTablePanel.getRowCount(); i++) {
                            if ((int) barangTablePanel.getValueAt(i, 0) == id) {
                                barangTablePanel.setValueAt(hargaBaru, i, 2); // Update harga
                                barangTablePanel.setValueAt(stokBaru, i, 3);  // Update stok
                                break;
                            }
                        }
    
                        JOptionPane.showMessageDialog(owner, "Barang berhasil diperbarui!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(owner, "Barang dengan ID " + id + " tidak ditemukan.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "Input tidak valid.");
                }
            });
            add(btnEdit);
    
            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());
            add(btnBatal);
    
            // Aksi tombol Cari
            btnCari.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(fieldId.getText());
                    Barang barangDitemukan = null;
    
                    // Cari barang berdasarkan ID
                    for (Barang barang : listBarang) {
                        if (barang.getId() == id) {
                            barangDitemukan = barang;
                            break;
                        }
                    }
    
                    if (barangDitemukan != null) {
                        // Jika ditemukan, tampilkan data barang di field
                        fieldHarga.setText(String.valueOf(barangDitemukan.getHarga()));
                        fieldStok.setText(String.valueOf(barangDitemukan.getStok()));
                    } else {
                        JOptionPane.showMessageDialog(owner, "Barang dengan ID " + id + " tidak ditemukan.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "ID tidak valid.");
                }
            });
        }
    }
    
}

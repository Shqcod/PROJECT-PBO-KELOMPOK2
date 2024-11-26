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
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            setSize(400, 300);
            setLocationRelativeTo(null);

            JLabel labelID = new JLabel("ID: ");
            gbc.gridx = 0; 
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            add(labelID, gbc);

            JTextField fieldId = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldId, gbc);
            
            JLabel labelNama = new JLabel("Nama Barang:");
            gbc.gridx = 0; 
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.NONE;
            add(labelNama, gbc);

            JTextField fieldNama = new JTextField(20);
            // fieldNama.setMaximumSize(new Dimension(100,25));
            gbc.gridx = 1;
            gbc. gridy = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldNama,gbc);

            JLabel labelKategori = new JLabel("Kategori:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(labelKategori, gbc);

            JTextField fieldKategori = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldKategori, gbc);

            JLabel labelHarga = new JLabel("Harga:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(labelHarga, gbc);

            JTextField fieldHarga = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldHarga, gbc);

            JLabel labelStok = new JLabel("Stok:");
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(labelStok, gbc);

            JTextField fieldStok = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldStok, gbc);

            JButton btnTambah = new JButton("Tambah");
            btnTambah.addActionListener(e -> {
                try { // Tambahkan barang ke tabel
                    int id = Integer.parseInt(fieldId.getText());

                      // Cek apakah ID sudah ada
                boolean idExists = false;
                for (Barang barang : listBarang) {
                    if (barang.getId() == id) {
                        idExists = true;
                        break;
                    }
                }

                if (idExists) {
                    // Jika ID sudah ada, tampilkan peringatan
                    JOptionPane.showMessageDialog(owner, "ID sudah ada. Silakan masukkan ID yang lain.");
                    return; // Jangan lanjutkan jika ID duplikat
                }

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

            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnTambah);
            buttonPanel.add(btnBatal);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(buttonPanel, gbc);

            pack();
        }
    }

    public static class MenuHapusBarang extends JDialog {
        private List<Barang> listBarang;
        public MenuHapusBarang(Frame owner, BarangTablePanel barangTablePanel, List<Barang> listBarang) {
            super(owner, "Hapus Barang", true);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            setSize(300, 150);
            setLocationRelativeTo(null);
    
            JLabel labelID = new JLabel("Id Barang: ");
            gbc.gridx = 0; 
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            add(labelID, gbc);

            JTextField fieldId = new JTextField();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldId, gbc);
    
            JButton btnHapus = new JButton("Hapus");
            btnHapus.addActionListener(e -> {
                // Step 1: Validasi input ID dari TextField
                try {
                    int id = Integer.parseInt(fieldId.getText());
                    Barang barangDihapus = null;
    
                    // Cari barang berdasarkan ID
                    for (Barang barang : listBarang) {
                        if (barang.getId() == id) {
                            barangDihapus = barang;
                            break;
                        }
                    }
    
                    if (barangDihapus != null) {
                        // Step 2: Tampilkan konfirmasi di OptionPane kedua
                        int konfirmasi = JOptionPane.showConfirmDialog(
                            owner,
                            "Data Barang:\n" +
                            "Nama Barang: " + barangDihapus.getNama() + "\n" +
                            "Kategori: " + barangDihapus.getKategori() + "\n" +
                            "Harga: " + barangDihapus.getHarga() + "\n" +
                            "Stok: " + barangDihapus.getStok() + "\n\n" +
                            "Apakah Anda yakin ingin menghapus barang ini?",
                            "Konfirmasi Hapus Barang",
                            JOptionPane.YES_NO_OPTION
                        );
    
                        if (konfirmasi == JOptionPane.YES_OPTION) {
                            // Step 3: Hapus barang dari list dan file
                            listBarang.remove(barangDihapus);
                            ListBarang.saveBarangToFile("barang.txt", listBarang);
    
                            // Hapus barang dari tabel GUI
                            for (int i = 0; i < barangTablePanel.getRowCount(); i++) {
                                if ((int) barangTablePanel.getValueAt(i, 0) == id) {
                                    barangTablePanel.removeRow(i);
                                    break;
                                }
                            }
    
                            JOptionPane.showMessageDialog(owner, "Barang berhasil dihapus!");
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(owner, "Barang dengan ID " + id + " tidak ditemukan.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(owner, "ID barang tidak valid!");
                }
            });
    
            JButton btnBatal = new JButton("Batal");
            btnBatal.addActionListener(e -> dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnHapus);
            buttonPanel.add(btnBatal);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(buttonPanel, gbc);
            
            pack();
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

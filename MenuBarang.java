import javax.swing.*;
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
        private Barang barangDitemukan;

        public MenuEditBarang(Frame owner, BarangTablePanel barangTablePanel, List<Barang> listBarang) {
            super(owner, "Edit Barang", true);
            this.listBarang = listBarang;
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            setSize(300, 150);
            setLocationRelativeTo(null);

            // Step 1: OptionPane untuk Input ID Barang
            String idInput = JOptionPane.showInputDialog(owner, "Masukkan ID Barang yang ingin diedit:");
            if (idInput == null) {
            // Jika user membatalkan input
            dispose();
            return;
            }

            // Validasi ID dan cari barang
            barangDitemukan = null;
            try {
                int id = Integer.parseInt(idInput);
                for (Barang barang : listBarang) {
                    if (barang.getId() == id) {
                    barangDitemukan = barang;
                    break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(owner, "ID tidak valid.");
                dispose();
                return;
            }

            if (barangDitemukan == null) {
                JOptionPane.showMessageDialog(owner, "Barang dengan ID " + idInput + " tidak ditemukan.");
                dispose();
                return;
            }

            // Step 2: Dialog untuk Edit Data Barang
            // Menampilkan Form dengan data lama
            setLayout(new GridBagLayout());
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel labelId = new JLabel("ID: ");
            gbc.gridx = 0; 
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            add(labelId, gbc);

            JTextField fieldId = new JTextField(String.valueOf(barangDitemukan.getId()));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldId, gbc);

            JLabel labelNama = new JLabel("Nama Barang:");
            gbc.gridx = 0; 
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.NONE;
            add(labelNama, gbc);

            JTextField fieldNama = new JTextField(barangDitemukan.getNama());
            gbc.gridx = 1;
            gbc. gridy = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldNama,gbc);

            JLabel labelKategori = new JLabel("Kategori:");
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(labelKategori, gbc);

            JTextField fieldKategori = new JTextField(barangDitemukan.getKategori());
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldKategori, gbc);

            JLabel labelHarga = new JLabel("Harga:");
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(labelHarga, gbc);

            JTextField fieldHarga = new JTextField(String.valueOf(barangDitemukan.getHarga()));
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldHarga, gbc);

            JLabel labelStok = new JLabel("Stok:");
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(labelStok, gbc);

            JTextField fieldStok = new JTextField(String.valueOf(barangDitemukan.getStok()));
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(fieldStok, gbc);

            JButton btnEdit = new JButton("Simpan");
            btnEdit.addActionListener(e -> {
                try {
                    // Ambil data dari input
                    int idBaru = Integer.parseInt(fieldId.getText());
                    String namaBaru = fieldNama.getText();
                    String kategoriBaru = fieldKategori.getText();
                    double hargaBaru = Double.parseDouble(fieldHarga.getText());
                    int stokBaru = Integer.parseInt(fieldStok.getText());

                // Validasi: Cek jika ID baru sudah digunakan oleh barang lain
                boolean idSudahAda = false;
                for (Barang barang : listBarang) {
                    if (barang.getId() == idBaru && barang != barangDitemukan) {
                        idSudahAda = true;
                        break;
                    }
                }
                if (idSudahAda) {
                    JOptionPane.showMessageDialog(owner, "ID baru sudah digunakan oleh barang lain.");
                    return;
                }

                // Update data barang
                barangDitemukan.setId(idBaru);
                barangDitemukan.setNama(namaBaru);
                barangDitemukan.setKategori(kategoriBaru);
                barangDitemukan.setHarga(hargaBaru);
                barangDitemukan.setStok(stokBaru);

                // Simpan perubahan ke file
                ListBarang.saveBarangToFile("barang.txt", listBarang);

                barangTablePanel.refreshTable(listBarang);
                // Update tabel GUI
                for (int i = 0; i < barangTablePanel.getRowCount(); i++) {
                    if ((int) barangTablePanel.getValueAt(i, 0) == barangDitemukan.getId()) {
                        barangTablePanel.setValueAt(idBaru, i, 0);
                        barangTablePanel.setValueAt(namaBaru, i, 1);
                        barangTablePanel.setValueAt(hargaBaru, i, 2);
                        barangTablePanel.setValueAt(stokBaru, i, 3);
                        barangTablePanel.setValueAt(kategoriBaru, i, 4);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(owner, "Barang berhasil diperbarui!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(owner, "Input tidak valid.");
            }
        });
        //add(btnEdit);

        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(e -> dispose());
        //add(btnBatal);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnBatal);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        pack();
        }
    
    }
    
}

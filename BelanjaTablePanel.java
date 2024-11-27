import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BelanjaTablePanel extends JPanel {
    JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryComboBox;
    private List<Barang> barangList;
    private ArrayList<Barang> keranjang;
    //private Akun customer;

    public BelanjaTablePanel(List<Barang> barangList) {
        this.barangList = barangList;
        this.keranjang = new ArrayList<>();

        setLayout(new BorderLayout());

        // Tabel
        String[] columnNames = {"ID", "Nama", "Harga", "Stok", "Kategori", "Tambah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Kategori
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel filterLabel = new JLabel("Kategori:");
        filterPanel.add(filterLabel);

        categoryComboBox = new JComboBox<>(getCategories());
        categoryComboBox.addItem("Semua");
        categoryComboBox.addActionListener(e -> filterTable());
        filterPanel.add(categoryComboBox);

        add(filterPanel, BorderLayout.NORTH);

        // Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnTambah = FormComponents.createInteractButton("Tambah", new Color(65, 195, 100), e -> tambahBarangKeKeranjang());
        JButton btnKeranjang = FormComponents.createInteractButton("Keranjang",new Color(65, 195, 100) ,e -> bukaKeranjang());
        JButton btnCheckout = FormComponents.createInteractButton("Checkout",new Color(65, 195, 100) ,e -> checkout());

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnKeranjang);
        buttonPanel.add(btnCheckout);
        add(buttonPanel, BorderLayout.SOUTH);

        populateTable(barangList);

    }

    private String[] getCategories() {
        return barangList.stream()
                .map(Barang::getKategori)
                .distinct()
                .toArray(String[]::new);
    }

    private void populateTable(List<Barang> barangList) {
        tableModel.setRowCount(0); // Hapus data lama
        for (Barang barang : barangList) {
            tableModel.addRow(new Object[]{
                    barang.getId(),
                    barang.getNama(),
                    barang.getHarga(),
                    barang.getStok(),
                    barang.getKategori()
            });
        }
    }

    private void filterTable() {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        List<Barang> filteredList;
        if (selectedCategory.equals("Semua")) {
            filteredList = barangList;
        } else {
            filteredList = barangList.stream()
                    .filter(barang -> barang.getKategori().equalsIgnoreCase(selectedCategory))
                    .collect(Collectors.toList());
        }
        populateTable(filteredList);
    }
    private void tambahBarangKeKeranjang() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) table.getValueAt(selectedRow, 0);
            Barang barang = barangList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    
            if (barang != null) {
                if (barang.getStok() > 0) {
                    String inputJumlah = JOptionPane.showInputDialog(this, 
                            "Masukkan jumlah barang yang ingin ditambahkan (stok tersedia: " + barang.getStok() + "):", 
                            "Tambah ke Keranjang", JOptionPane.PLAIN_MESSAGE);
                    
                    if (inputJumlah != null && !inputJumlah.trim().isEmpty()) {
                        try {
                            int jumlah = Integer.parseInt(inputJumlah);
    
                            if (jumlah > 0 && jumlah <= barang.getStok()) {
                                barang.setStok(barang.getStok() - jumlah);
    
                                // Periksa apakah barang sudah ada di keranjang
                                Barang barangDiKeranjang = keranjang.stream()
                                        .filter(b -> b.getId() == id)
                                        .findFirst()
                                        .orElse(null);
    
                                if (barangDiKeranjang != null) {
                                    // Tambahkan jumlah barang jika sudah ada di keranjang
                                    barangDiKeranjang.setStok(barangDiKeranjang.getStok() + jumlah);
                                } else {
                                    // Tambahkan barang baru ke keranjang
                                    keranjang.add(new Barang(barang.getId(), barang.getNama(), barang.getHarga(), jumlah, barang.getKategori()));
                                }
    
                                JOptionPane.showMessageDialog(this, "Barang ditambahkan ke keranjang!");
                                populateTable(barangList); // Perbarui tabel untuk menunjukkan stok yang berkurang
                            } else {
                                JOptionPane.showMessageDialog(this, "Jumlah tidak valid atau melebihi stok!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Input jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Stok habis!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void bukaKeranjang() {
        JFrame keranjangFrame = new JFrame("Keranjang");
        keranjangFrame.setContentPane(new KeranjangPanel(keranjang));
        keranjangFrame.setSize(600, 400);
        keranjangFrame.setLocationRelativeTo(this);
        keranjangFrame.setVisible(true);
    }

    private void checkout() {
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang kosong!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        String metodePembayaran = JOptionPane.showInputDialog(this, 
                "Masukkan metode pembayaran (contoh: QRIS/COD/Bank):", 
                "Metode Pembayaran", JOptionPane.PLAIN_MESSAGE);
    
        if (metodePembayaran == null || metodePembayaran.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Metode pembayaran tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Generate ID transaksi
        String idTransaksi = "TRX" + System.currentTimeMillis();
        String keterangan = "Menunggu Konfirmasi";

        List<Transaksi> listTransaksi = new ArrayList<>();

        double totalHarga = 0;
        for (Barang barang : keranjang){
            totalHarga += barang.getHarga() * barang.getStok();
        }

        Transaksi transaksi = new Transaksi(idTransaksi, new Akun(),keranjang, metodePembayaran, totalHarga, keterangan );

        
        // Simpan transaksi ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaksi.txt", true))) {
            StringBuilder barangString = new StringBuilder();
    
            for (Barang barang : keranjang) {
                barangString.append(String.format("[%s,%.2f,%d],", 
                    barang.getNama(), barang.getHarga(), barang.getStok()));
            }

    
            String transaksilist = String.format("%s;%s;%s;%s;%.2f;%s\n", 
                    idTransaksi, "Customer", metodePembayaran, barangString.toString(), totalHarga, keterangan);
    
            writer.write(transaksilist);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi ke file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listTransaksi.add(transaksi);
        keranjang.clear(); // Bersihkan keranjang setelah checkout
        JOptionPane.showMessageDialog(this, "Checkout berhasil! Data transaksi disimpan.");
    }
    
    


}

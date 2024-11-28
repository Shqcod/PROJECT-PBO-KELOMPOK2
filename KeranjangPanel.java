import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KeranjangPanel extends JPanel {
    JTable table;
    private DefaultTableModel tableModel;

    public KeranjangPanel(List<Barang> keranjang, List<Barang> barangList, CustomerBelanjaPanel customerBelanjaPanel) {
        setLayout(new BorderLayout());

        // Tabel
        String[] columnNames = {"ID", "Nama", "Harga", "Jumlah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        populateTable(keranjang);

        // Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnHapus = new JButton("Hapus Barang");

        buttonPanel.add(btnHapus);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tombol Hapus
        btnHapus.addActionListener(e -> hapusBarang(keranjang, barangList, customerBelanjaPanel));
    }

    private void populateTable(List<Barang> keranjang) {
        tableModel.setRowCount(0); // Hapus data lama
        for (Barang barang : keranjang) {
            tableModel.addRow(new Object[]{
                    barang.getId(),
                    barang.getNama(),
                    barang.getHarga(),
                    barang.getStok() // Stok di sini berfungsi sebagai jumlah barang
            });
        }
    }

    private void hapusBarang(List<Barang> keranjang, List<Barang> barangList, CustomerBelanjaPanel customerBelanjaPanel) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) table.getValueAt(selectedRow, 0);
            int jumlahDihapus = (int) table.getValueAt(selectedRow, 3);

            keranjang.removeIf(barang -> barang.getId() == id);

            // Kembalikan stok barang di barangList
            Barang barangDiList = barangList.stream()
                    .filter(b -> b.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (barangDiList != null) {
                barangDiList.setStok(barangDiList.getStok() + jumlahDihapus);
            }

            populateTable(keranjang);
            customerBelanjaPanel.populateTable(barangList); // Perbarui tabel utama
            JOptionPane.showMessageDialog(this, "Barang berhasil dihapus dari keranjang!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}

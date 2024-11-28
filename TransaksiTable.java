import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

public class TransaksiTable extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public TransaksiTable() {
        setTitle("Daftar Transaksi");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat tabel dan modelnya
        String[] columnNames = {"ID Transaksi", "Customer", "Metode Pembayaran", "Barang yang Dibeli", "Total Harga", "Keterangan"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        setTableColumnWidths();
        
        // Membaca data transaksi dari file dan menambahkannya ke tabel
        loadTransaksiData();

        // Menambahkan JScrollPane untuk tabel
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void loadTransaksiData() {
        String fileName = "transaksi.txt";  // Nama file transaksi

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Memisahkan data berdasarkan tanda titik koma
                String[] transaksiData = line.split(";");
                
                // Mengambil dan memformat data transaksi
                String idTransaksi = transaksiData[0];
                String customer = transaksiData[1];
                String metodePembayaran = transaksiData[2];
                String barangDibeli = transaksiData[3].replace(",", " "); // Menggabungkan barang dalam satu kolom
                String totalHarga = transaksiData[4];
                String keterangan = transaksiData[5];

                // Menambahkan data transaksi ke dalam model tabel
                model.addRow(new Object[] {idTransaksi, customer, metodePembayaran, barangDibeli, totalHarga, keterangan});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file transaksi", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setTableColumnWidths() {
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // ID Transaksi
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Customer
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Metode Pembayaran
        table.getColumnModel().getColumn(3).setPreferredWidth(250); // Barang yang Dibeli
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Total Harga
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Keterangan
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransaksiTable().setVisible(true);
        });
    }
}

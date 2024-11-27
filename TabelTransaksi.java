import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabelTransaksi extends JPanel {
    private List<Transaksi> listTransaksi;
    JTable table;
    private DefaultTableModel tableModel;

    public TabelTransaksi(List<Transaksi> listTransaksi) {
        setLayout(new BorderLayout());

        // Tabel
        String[] columnNames = {"ID Transaksi", "Pelanggan", "Metode Pembayaran", "Barang yang Dibeli", "Total Harga", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnKonfirmasi = FormComponents.createInteractButton("Konfirmasi", new Color(65, 195, 100), e -> konfirmasiTransaksi());
        buttonPanel.add(btnKonfirmasi);
        // JButton btnHapusSemua = FormComponents.createInteractButton("Hapus Semua", new Color(65, 195, 100), e -> hapusSemuaTransaksi());
        // buttonPanel.add(btnHapusSemua);
        add(buttonPanel, BorderLayout.SOUTH);

        loadTransaksiData();
        adjustColumnWidths();
    }

    private void loadTransaksiData() {
        String fileName = "transaksi.txt";  // Nama file transaksi

        tableModel.setRowCount(0);

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
                tableModel.addRow(new Object[] {idTransaksi, customer, metodePembayaran, barangDibeli, totalHarga, keterangan});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal membaca file transaksi", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Menyesuaikan lebar kolom berdasarkan panjang data
    private void adjustColumnWidths() {
        for (int i = 0; i < table.getColumnCount(); i++) {
            int columnIndex = i;
            TableColumn column = table.getColumnModel().getColumn(columnIndex);
            int maxWidth = 0;
            // Menghitung panjang karakter terbesar pada kolom tersebut
            for (int row = 0; row < table.getRowCount(); row++) {
                Object value = table.getValueAt(row, columnIndex);
                int length = value != null ? value.toString().length() : 0;
                maxWidth = Math.max(maxWidth, length);
            }
            // Menyesuaikan lebar kolom berdasarkan panjang data
            column.setPreferredWidth(maxWidth * 10);  // Menyesuaikan lebar (dikali 10 untuk padding)
        }
    }
    public DefaultTableModel getModel() {
        return tableModel;
    }

    public void konfirmasiTransaksi() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String idTransaksi = (String) table.getValueAt(selectedRow, 0);

            // Menampilkan pop-up konfirmasi
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Konfirmasi transaksi ini?", 
                    "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Mengubah keterangan transaksi menjadi "Sukses"
                updateKeterangan(idTransaksi);

                // Refresh tabel untuk menampilkan status terbaru
                loadTransaksiData();

                JOptionPane.showMessageDialog(this, "Transaksi berhasil dikonfirmasi.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih transaksi untuk dikonfirmasi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

     // Fungsi untuk memperbarui keterangan transaksi di file
    private void updateKeterangan(String idTransaksi) {
        List<String> transaksiList = new ArrayList<>();
        String fileName = "transaksi.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transaksiData = line.split(";");
                if (transaksiData[0].equals(idTransaksi)) {
                    // Update keterangan menjadi "Sukses"
                    transaksiData[5] = "Sukses";
                }
                // Menambahkan transaksi ke dalam list
                transaksiList.add(String.join(";", transaksiData));
            }

            // Menulis ulang file dengan data yang telah diperbarui
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String transaksi : transaksiList) {
                    writer.write(transaksi + "\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui file transaksi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getRowCount() {
        return tableModel.getRowCount();
    }

    // Menambahkan metode getValueAt()
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableModel.getValueAt(rowIndex, columnIndex);
    }

    // Menambahkan metode removeRow()
    public void removeRow(int rowIndex) {
        tableModel.removeRow(rowIndex);
    }

    public void setValueAt(Object value, int rowIndex, int ColumnIndex){
        tableModel.setValueAt(value, rowIndex, ColumnIndex);
    }
    public void refreshTable(List<Barang> listBarang) {
        DefaultTableModel model = getModel();
        model.setRowCount(0); // Hapus semua baris
    
        for (Transaksi transaksi : listTransaksi) {
            model.addRow(new Object[] {
                transaksi.getID(),
                transaksi.getCustomer(),
                transaksi.getMetodePembayaran(),
                transaksi.getBarangDibeli(),
                transaksi.getTotalHargaBarang(),
                transaksi.getKeterangan()
            });
        }
    }
    


}

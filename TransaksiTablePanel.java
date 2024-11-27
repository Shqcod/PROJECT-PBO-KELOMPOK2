import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TransaksiTablePanel extends JPanel {
    private List<Transaksi> listTransaksi;
    JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryComboBox;

    public TransaksiTablePanel(List<Transaksi> listTransaksi) {
        setLayout(new BorderLayout());

        // Tabel
        String[] columnNames = {"ID Transaksi", "Pelanggan", "Metode Pembayaran", "Barang yang Dibeli", "Total Harga", "Keterangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Kategori
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel filterLabel = new JLabel("Kategori:");
        filterPanel.setBackground(Color.WHITE);
        filterPanel.add(filterLabel);

       // populateTable(listTransaksi);
        adjustColumnWidths();
    }

    private String[] getCategories(List<Barang> barangList) {
        return barangList.stream()
                .map(Barang::getKategori)
                .distinct()
                .toArray(String[]::new);
    }

    // private void populateTable(List<Transaksi> listTransaksi) {
    //     tableModel.setRowCount(0); // Hapus data lama
    //     for (Transaksi transaksi : listTransaksi) {
    //         tableModel.addRow(new Object[]{
    //                 barang.getId(),
    //                 barang.getNama(),
    //                 barang.getHarga(),
    //                 barang.getStok(),
    //                 barang.getKategori()
    //         });
    //     }
    // }

    private void filterTable(List<Barang> barangList) {
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        List<Barang> filteredList;
        if (selectedCategory.equals("Semua")) {
            filteredList = barangList;
        } else {
            filteredList = barangList.stream()
                    .filter(barang -> barang.getKategori().equalsIgnoreCase(selectedCategory))
                    .collect(Collectors.toList());
        }
        //populateTable(filteredList);
        adjustColumnWidths();
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

    public void tambahBarang(Barang barang){
        tableModel.addRow(new Object[] {
            barang.getId(),
            barang.getNama(),
            barang.getHarga(),
            barang.getStok(),
            barang.getKategori()
        });
    }

    public void editBarang(int id, Barang barang) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.setValueAt(barang.getNama(), i, 1);
                tableModel.setValueAt(barang.getHarga(), i, 2);
                tableModel.setValueAt(barang.getStok(), i, 3);
                tableModel.setValueAt(barang.getKategori(), i, 4);
                break;
            }
        }
    }

    public void hapusBarang(int id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    public int getSelectedRowId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) table.getValueAt(selectedRow, 0);  // Mengambil ID barang dari kolom pertama
        }
        return -1;  // Jika tidak ada baris yang dipilih
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
    
        for (Barang barang : listBarang) {
            model.addRow(new Object[] {
                barang.getId(),
                barang.getNama(),
                barang.getHarga(),
                barang.getStok(),
                barang.getKategori()
            });
        }
    }
    


}

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListBarang {
    // Membaca barang dari file
    public static List<Barang> loadBarangFromFile(String filePath) {
        List<Barang> listBarang = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Validasi format
                    int id = Integer.parseInt(parts[0]);
                    String nama = parts[1];
                    double harga = Double.parseDouble(parts[2]);
                    int stok = Integer.parseInt(parts[3]);
                    String kategori = parts[4];
                    listBarang.add(new Barang(id, nama, harga, stok, kategori));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat data barang: " + e.getMessage());
        }
        return listBarang;
    }

    // Menyimpan barang ke file
    public static void saveBarangToFile(String filePath, List<Barang> listBarang) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Barang barang : listBarang) {
                String line = barang.getId() + "," +
                              barang.getNama() + "," +
                              barang.getHarga() + "," +
                              barang.getStok() + "," +
                              barang.getKategori();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data barang: " + e.getMessage());
        }
    }

    public static void tambahBarang(String filePath, List<Barang> listBarang, Barang barang) {
        // Tambahkan barang baru ke daftar
        listBarang.add(barang);
    
        // Simpan daftar barang yang diperbarui ke file
        saveBarangToFile(filePath, listBarang);
    
        // Informasikan bahwa barang berhasil ditambahkan
        //System.out.println("Barang berhasil ditambahkan: " + barang.getNama());
    }
    
    

    // Generate ID baru untuk barang
    public static int generateId(List<Barang> listBarang) {
        int maxId = 0;
        for (Barang barang : listBarang) {
            if (barang.getId() > maxId) {
                maxId = barang.getId();
            }
        }
        return maxId + 1;
    }
}

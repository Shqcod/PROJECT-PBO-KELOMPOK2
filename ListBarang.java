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
    
    }
    public static void updateStokBarang(List<Barang> keranjang){
        String filePath = "barang.txt";
        List<Barang> updatedBarangList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] barangData = line.split(",");
                int id = Integer.parseInt(barangData[0]);
                String nama = barangData[1];
                double harga = Double.parseDouble(barangData[2]);
                int stok = Integer.parseInt(barangData[3]);
                String kategori = barangData[4];

                // Jika barang ada dalam keranjang, kurangi stok
                for (Barang barangDiKeranjang : keranjang) {
                    if (barangDiKeranjang.getId() == id) {
                        stok -= barangDiKeranjang.getStok(); // Kurangi stok sesuai jumlah yang dibeli
                    }
                }

                // Membuat objek barang baru dengan stok yang telah diperbarui
                updatedBarangList.add(new Barang(id, nama, harga, stok, kategori));
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file barang: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Barang barang : updatedBarangList) {
                writer.write(String.format("%d,%s,%.2f,%d,%s\n",
                barang.getId(), barang.getNama(), barang.getHarga(), barang.getStok(), barang.getKategori()));
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file barang: " + e.getMessage());
        }
    }

    public static void editBarang(String filePath, List<Barang> listBarang, int idBarang, double hargaBaru, int stokBaru){
        for (Barang barang : listBarang) {
            if (barang.getId() == idBarang) {
                // Perbarui harga dan stok
                barang.setHarga(hargaBaru);
                barang.setStok(stokBaru);
    
                // Simpan daftar barang ke file
                saveBarangToFile(filePath, listBarang);
                System.out.println("Barang berhasil diperbarui: " + barang.getNama());
                return;
            }
        }
    }
    
    public static void hapusBarang(String filePath, List<Barang> listBarang, int idBarang) {
        Barang barangDihapus = null;
        for (Barang barang : listBarang) {
            if (barang.getId() == idBarang) {
                barangDihapus = barang;
                break;
            }
        }
        if (barangDihapus != null) {
            listBarang.remove(barangDihapus);
            saveBarangToFile(filePath, listBarang);
            System.out.println("Barang berhasil dihapus: " + barangDihapus.getNama());
        } else {
            System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        }
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

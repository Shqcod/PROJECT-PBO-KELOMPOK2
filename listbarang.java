import java.io.*;
import java.util.*;

public class ListBarang {
    private ArrayList<Barang> listBarang;
    private static final String FILE_NAME = "barang.txt";

    public ListBarang() {
        listBarang = new ArrayList<>();
        loadBarangFromFile();
    }

    // Membaca daftar barang dari file
    private void loadBarangFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String nama = data[0];
                    double harga = Double.parseDouble(data[1]);
                    int stok = Integer.parseInt(data[2]);
                    listBarang.add(new Barang(nama, harga, stok));
                }
            }
        } catch (IOException e) {
            System.out.println("Error membaca file barang: " + e.getMessage());
        }
    }

    // Menyimpan daftar barang ke file
    private void saveBarangToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Barang barang : listBarang) {
                bw.write(barang.getNama() + "," + barang.getHarga() + "," + barang.getStok() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error menulis ke file barang: " + e.getMessage());
        }
    }

    // Menambah barang ke daftar
    public void tambahBarang(Barang barang) {
        listBarang.add(barang);
        saveBarangToFile();
    }

    // Menghapus barang dari daftar
    public void hapusBarang(String namaBarang) {
        Barang barang = cariBarang(namaBarang);  // Find the Barang object by name
        if (barang != null) {
            listBarang.remove(barang);  // Remove the found Barang object
            saveBarangToFile();  // Save updated list to file
            System.out.println("Barang " + namaBarang + " berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan!");
        }
    }

    // Mengedit barang
    public void editBarang(String namaBarang, double hargaBaru, int stokBaru) {
        for (Barang barang : listBarang) {
            if (barang.getNama().equalsIgnoreCase(namaBarang)) {
                barang.setHarga(hargaBaru);
                barang.setStok(stokBaru);
                saveBarangToFile();
                return;
            }
        }
        System.out.println("Barang tidak ditemukan!");
    }

    // Menampilkan daftar barang
    public void lihatBarang() {
        if (listBarang.isEmpty()) {
            System.out.println("Daftar barang kosong!");
            return;
        }
        System.out.println("=== Daftar Barang ===");
        for (Barang barang : listBarang) {
            System.out.println(barang);
        }
    }

    // Mencari barang berdasarkan nama
    public Barang cariBarang(String namaBarang) {
        for (Barang barang : listBarang) {
            if (barang.getNama().equalsIgnoreCase(namaBarang)) {
                return barang;
            }
        }
        return null;
    }
}

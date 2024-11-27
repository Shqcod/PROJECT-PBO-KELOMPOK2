import java.io.*;
import java.util.ArrayList;

public class FileTransaksi {
    public static ArrayList<Transaksi> bacaTransaksiDariFile(String filePath) {
        ArrayList<Transaksi> transaksiList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String idTransaksi = parts[0];
                    Akun customer = new Akun(parts[1], "", "", "customer");
                    String metodePembayaran = parts[2];
                    String keterangan = parts[3];
                    double totalHarga = Double.parseDouble(parts[4]);

                    ArrayList<Barang> barangDibeli = new ArrayList<>();
                    String[] barangParts = parts[4].replace("[", "").replace("]", "").split(",");
                    for (String barangStr : barangParts) {
                        String[] barangData = barangStr.split("-");
                        int id = Integer.parseInt(barangData[0]);
                        String nama = barangData[1];
                        double harga = Double.parseDouble(barangData[2]);
                        int jumlah = Integer.parseInt(barangData[3]);
                        String kategori = barangData[4];
                        barangDibeli.add(new Barang(id, nama, harga, jumlah, kategori));
                    }

                    transaksiList.add(new Transaksi(idTransaksi, customer, barangDibeli, metodePembayaran, totalHarga, keterangan));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transaksiList;
    }

    public static void simpanTransaksiKeFile(ArrayList<Transaksi> transaksiList, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaksi transaksi : transaksiList) {
                StringBuilder barangStr = new StringBuilder();
                for (Barang barang : transaksi.getBarangDibeli()) {
                    barangStr.append(String.format("[%d-%s-%.2f-%d-%s],", 
                        barang.getId(), barang.getNama(), barang.getHarga(), barang.getStok(), barang.getKategori()));
                }
                bw.write(String.format("%s;%s;%s;%s;%s", 
                        transaksi.getID(), 
                        transaksi.getCustomer().getId(), 
                        transaksi.getMetodePembayaran(), 
                        transaksi.getKeterangan(), 
                        barangStr.toString()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.util.ArrayList;

public class Transaksi {
    private static int counter = 1; // Digunakan untuk membuat ID transaksi unik
    private String id;
    private Customer pelanggan;
    private ArrayList<Barang> daftarBarang;

    public Transaksi(Customer pelanggan, ArrayList<Barang> daftarBarang) {
        this.id = "TRX" + counter++;
        this.pelanggan = pelanggan;
        this.daftarBarang = daftarBarang;
    }

    public String getId() {
        return id;
    }

    public Customer getPelanggan() {
        return pelanggan;
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Transaksi: ").append(id).append("\n");
        sb.append("Pelanggan: ").append(pelanggan.getUsername()).append("\n");
        sb.append("Barang: \n");
        for (Barang barang : daftarBarang) {
            sb.append(" - ").append(barang.getNama()).append(", Harga: ").append(barang.getHarga())
              .append(", Jumlah: ").append(barang.getStok()).append("\n");
        }
        return sb.toString();
    }
}

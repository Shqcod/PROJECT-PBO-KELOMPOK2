import java.util.ArrayList;

public class Transaksi {
    private static int idCounter = 1;
    private int transaksiId;
    private ArrayList<String> barang;
    private String statusTransaksi;

    public Transaksi(ArrayList<String> barang) {
        this.transaksiId = idCounter++;
        this.barang = barang;
        this.statusTransaksi = "Invoice Selesai"; // Status default untuk transaksi yang berhasil
    }

    public void tampilkanDetailTransaksi() {
        System.out.println("Transaksi ID: " + transaksiId);
        System.out.println("Status Transaksi: " + statusTransaksi);
        System.out.println("Barang dalam Transaksi:");
        for (String b : barang) {
            System.out.println("- " + b);
        }
    }
}

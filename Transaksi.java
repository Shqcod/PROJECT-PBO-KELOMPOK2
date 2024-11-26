import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Barang> barangDibeli;
    private String customer;

    public Transaksi(String customer, ArrayList<Barang> barangDibeli) {
        this.customer = customer;
        this.barangDibeli = new ArrayList<>(barangDibeli);
    }

    public void tampilkanTransaksi() {
        System.out.println("Transaksi oleh: " + customer);
        System.out.println("Barang yang Dibeli:");
        for (Barang barang : barangDibeli) {
            System.out.println(barang);
        }
    }

    public ArrayList<Barang> getBarangDibeli() {
        return barangDibeli;
    }

    public String getCustomer() {
        return customer;
    }
}

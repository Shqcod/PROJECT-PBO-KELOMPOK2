import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Barang> barangDibeli;
    private Akun customer;
    private String metodePembayaran;
    private String keterangan;
    private String idTransaksi;
    private double totalHargaBarang; // Tambahkan atribut ini

    public Transaksi(String idTransaksi, Akun customer, ArrayList<Barang> barangDibeli, String metodePembayaran, double totalHarga, String keterangan) {
        this.idTransaksi = idTransaksi; // Sesuai parameter
        this.customer = customer;
        this.barangDibeli = new ArrayList<>(barangDibeli); // Properti diatur lebih dulu
        this.metodePembayaran = metodePembayaran;
        this.keterangan = keterangan; // Nilai default
        this.totalHargaBarang = setTotalHargaBarang(); // Hitung setelah barangDibeli diatur
    }

    // Getter untuk atribut baru
    public double getTotalHargaBarang() {
        return totalHargaBarang;
    }

    // Getter dan setter lainnya
    public String getID() {
        return idTransaksi;
    }

    public ArrayList<Barang> getBarangDibeli() {
        return barangDibeli;
    }

    public Akun getCustomer() {
        return customer;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // Method untuk menghitung total harga barang
    private double setTotalHargaBarang() {
        double total = 0;
        for (Barang barang : barangDibeli) {
            total += barang.getHarga() * barang.getStok(); // Harga * jumlah barang
        }
        return total;
    }

    public void konfirmasiKeterangan(String keterangan){
        this.keterangan = "Sukses";
    }

    public void updateTransaksi(ArrayList<Barang> barangBaru, String keteranganBaru) {
        this.barangDibeli = barangBaru;
        this.totalHargaBarang = setTotalHargaBarang(); // Hitung ulang total harga
        this.keterangan = keteranganBaru;
    }

    @Override
    public String toString() {
        return "Transaksi{" +
                "idTransaksi='" + idTransaksi + '\'' +
                ", customer='" + customer.getUsername() + '\'' +
                ", metodePembayaran='" + metodePembayaran + '\'' +
                ", totalHarga=" + totalHargaBarang +
                ", keterangan='" + keterangan + '\'' +
                ", barangDibeli=" + barangDibeli +
                '}';
    }
}

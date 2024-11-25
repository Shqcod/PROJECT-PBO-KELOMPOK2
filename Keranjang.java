import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> daftarBarang;

    public Keranjang() {
        this.daftarBarang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
        System.out.println(barang.getNama() + " berhasil ditambahkan ke keranjang.");
    }

    public void hapusBarang(Barang barang) {
        if (daftarBarang.remove(barang)) {
            System.out.println(barang.getNama() + " berhasil dihapus dari keranjang.");
        } else {
            System.out.println(barang.getNama() + " tidak ditemukan di keranjang.");
        }
    }

    public void lihatBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("Daftar Barang di Keranjang:");
            for (Barang barang : daftarBarang) {
                System.out.println(barang);
            }
        }
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }
}

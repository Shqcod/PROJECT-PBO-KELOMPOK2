import java.util.ArrayList;

public class Keranjang {
    private ArrayList<String> daftarBarang;

    public Keranjang() {
        this.daftarBarang = new ArrayList<>();
    }

    public void tambahBarang(String barang) {
        daftarBarang.add(barang);
        System.out.println(barang + " telah ditambahkan ke keranjang.");
    }

    public void tampilkanBarang() {
        System.out.println("Barang di keranjang:");
        for (String barang : daftarBarang) {
            System.out.println("- " + barang);
        }
    }

    public ArrayList<String> getBarang() {
        return daftarBarang;
    }

    public void kosongkanKeranjang() {
        daftarBarang.clear();
        System.out.println("Keranjang telah dikosongkan.");
    }
}

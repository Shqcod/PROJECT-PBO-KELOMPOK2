import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> daftarBarang;

    public Keranjang() {
        this.daftarBarang = new ArrayList<>();
    }


    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }
}

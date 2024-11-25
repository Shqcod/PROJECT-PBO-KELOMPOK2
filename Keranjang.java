import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    public void tambahBarang(Barang b) {
        barang.add(b);
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    @Override
    public String toString() {
        return "Keranjang{" +
                "barang=" + barang +
                '}';
    }
}

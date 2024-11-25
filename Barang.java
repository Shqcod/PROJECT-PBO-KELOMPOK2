import java.util.Objects;

public class Barang {
    private String nama;
    private double harga;

    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barang barang = (Barang) o;
        return Objects.equals(nama, barang.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nama);
    }

    @Override
    public String toString() {
        return "Barang: " + nama + ", Harga: " + harga;
    }
}


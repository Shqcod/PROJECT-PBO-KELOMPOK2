import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> barang;

    public ListBarang() {
        this.barang = new ArrayList<>();
    }

    public void addBarang(Barang barang) {
        this.barang.add(barang);
    }

    public void removeBarang(String idBarang) {
        barang.removeIf(b -> b.getId().equals(idBarang));
    }

    public void updateBarang(String idBarang, Barang barangBaru) {
        for (int i = 0; i < barang.size(); i++) {
            if (barang.get(i).getId().equals(idBarang)) {
                barang.set(i, barangBaru);
                break;
            }
        }
    }
}


import java.util.ArrayList;

public class AdminDriver {
    private Admin akun;
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver(Admin akun, ListBarang listBarang) {
        this.akun = akun;
        this.listBarang = listBarang;
        this.listTransaksi = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        listBarang.tambahBarang(barang);
    }

    public void hapusBarang(String namaBarang) {
        Barang barang = listBarang.cariBarang(namaBarang);
        if (barang != null) {
            listBarang.hapusBarang(namaBarang);
            System.out.println("Barang " + namaBarang + " berhasil dihapus.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void editBarang(Barang barangLama, Barang barangBaru) {
        listBarang.hapusBarang(barangLama.getNama());
        listBarang.tambahBarang(barangBaru);
    }

    public void lihatTransaksi() {
        if (listTransaksi.isEmpty()) {
            System.out.println("Tidak ada transaksi yang tersedia.");
        } else {
            for (Transaksi transaksi : listTransaksi) {
                System.out.println(transaksi);
            }
        }
    }

    public Transaksi cariTransaksi(String idTransaksi) {
        for (Transaksi transaksi : listTransaksi) {
            if (transaksi.getId().equalsIgnoreCase(idTransaksi)) {
                return transaksi;
            }
        }
        return null; // Transaksi tidak ditemukan
    }

    public void terimaTransaksi(Transaksi t) {
        if (listTransaksi.contains(t)) {
            System.out.println("Transaksi dengan ID " + t.getId() + " telah diterima.");
            listTransaksi.remove(t);
        } else {
            System.out.println("Transaksi tidak ditemukan.");
        }
    }
}

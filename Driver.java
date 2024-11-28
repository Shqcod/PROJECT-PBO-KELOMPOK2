import java.util.List;

public interface Driver {
    // Metode terkait Akun
    void addAkun(Akun akun);
    Akun getAkunByUsername(String username);
    List<Akun> getAllAkun();
    void updateAkun(String username, String newPassword);
    void deleteAkun(String username);

    // Metode terkait ListBarang
    void addBarang(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangById(int id);
    void updateBarang(int id, double hargaBaru, int stokBaru);
    void deleteBarang(int id);

    // Metode terkait Transaksi
    void addTransaksi(Transaksi transaksi);
    List<Transaksi> getAllTransaksi();
    List<Transaksi> getTransaksiByCustomer(String username);
    Transaksi getTransaksiById(String id);
    void updateTransaksi(String id, String keteranganBaru);
    void deleteTransaksi(String id);
}

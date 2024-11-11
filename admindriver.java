public class AdminDriver extends Driver {
    private ListBarang listBarang;
    private ArrayList<Transaksi> listTransaksi;

    public AdminDriver(Akun akun) {
        this.akun = akun;
        this.listBarang = new ListBarang();
        this.listTransaksi = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        listBarang.addBarang(barang);
        System.out.println("Barang berhasil ditambahkan.");
    }

    public void hapusBarang(String idBarang) {
        listBarang.removeBarang(idBarang);
        System.out.println("Barang berhasil dihapus.");
    }

    public void editBarang(String idBarang, Barang barangBaru) {
        listBarang.updateBarang(idBarang, barangBaru);
        System.out.println("Barang berhasil diedit.");
    }

    public void terimaTransaksi(Transaksi transaksi) {
        listTransaksi.add(transaksi);
        System.out.println("Transaksi diterima.");
    }

    @Override
    public void login() {
        System.out.println("Admin berhasil login.");
    }
}


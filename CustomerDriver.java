public class CustomerDriver extends Driver {
    private Customer akun;
    private Transaksi transaksi;
    private ListBarang barang;

    public CustomerDriver(Customer akun, ListBarang barang) {
        this.akun = akun;
        this.barang = barang;
    }

    public void lihatBarang() {
        barang.lihatBarang();
    }

    public void tambahKeKeranjang(Barang b) {
        akun.getKeranjang().tambahBarang(b);
        System.out.println("Barang ditambahkan ke keranjang: " + b);
    }

    public void checkout() {
        transaksi = new Transaksi(akun, akun.getKeranjang().getBarang());
        System.out.println("Checkout berhasil! Transaksi: " + transaksi);
    }

    @Override
    public void login() {
        System.out.println("Customer " + akun.getId() + " berhasil login.");
    }
}

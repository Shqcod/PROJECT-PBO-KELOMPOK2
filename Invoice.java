public class Invoice {
    private Transaksi transaksi;
    private String metodePembayaran;

    public Invoice(Transaksi transaksi, String metodePembayaran) {
        this.transaksi = transaksi;
        this.metodePembayaran = metodePembayaran;
    }

    public void tampilkanInvoice() {
        System.out.println("=== Invoice ===");
        transaksi.tampilkanTransaksi();
        System.out.println("Metode Pembayaran: " + metodePembayaran);
    }
}

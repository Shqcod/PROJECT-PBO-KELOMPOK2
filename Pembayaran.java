public class Pembayaran {
    public static void prosesPembayaran(Transaksi transaksi, String metodePembayaran) {
        System.out.println("Proses pembayaran dengan metode: " + metodePembayaran);
        Invoice invoice = new Invoice(transaksi, metodePembayaran);
        invoice.tampilkanInvoice();
    }
}

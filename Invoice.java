public class Invoice {
    private Transaksi transaksi;
    private Pembayaran pembayaran;
    private String status;

    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
        this.status = "Menunggu Konfirmasi";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return transaksi.toString() + 
               "Metode Pembayaran: " + pembayaran.getTipePembayaran() + "\n" +
               "Status: " + status;
    }
}

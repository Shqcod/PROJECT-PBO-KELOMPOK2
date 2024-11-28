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
        StringBuilder invoiceDetails = new StringBuilder();
        invoiceDetails.append("===== INVOICE =====\n");
        invoiceDetails.append("ID Transaksi: ").append(transaksi.getID()).append("\n");
        invoiceDetails.append("Pelanggan: ").append(transaksi.getCustomer().getUsername()).append("\n");
        invoiceDetails.append("Metode Pembayaran: ").append(pembayaran.getTipePembayaran()).append("\n");
        invoiceDetails.append("Status: ").append(status).append("\n");
        invoiceDetails.append("Barang yang Dibeli:\n");

        for (Barang barang : transaksi.getBarangDibeli()) {
            invoiceDetails.append(" - ").append(barang.getNama())
                    .append(", Harga: ").append(barang.getHarga())
                    .append(", Jumlah: ").append(barang.getStok()).append("\n");
        }

        invoiceDetails.append("Total Harga: ").append(transaksi.getTotalHargaBarang()).append("\n");
        invoiceDetails.append("===================\n");
        return invoiceDetails.toString();
    }
}

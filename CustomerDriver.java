import java.util.ArrayList;

public class CustomerDriver {
    private String customerName;
    private String customerId;
    private ArrayList<Transaksi> riwayatTransaksi;

    public CustomerDriver(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.riwayatTransaksi = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void tambahTransaksi(Transaksi transaksi) {
        riwayatTransaksi.add(transaksi);
    }

    public void tampilkanRiwayatTransaksi() {
        System.out.println("Riwayat Transaksi untuk " + customerName + ":");
        for (Transaksi transaksi : riwayatTransaksi) {
            transaksi.tampilkanDetailTransaksi();
        }
    }
}

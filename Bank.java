public class Bank extends Pembayaran {
    public Bank(String id) {
        super(id);
    }

    @Override
    public String getTipePembayaran() {
        return "Bank Transfer";
    }
}
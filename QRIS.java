public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id);
    }

    @Override
    public String getTipePembayaran() {
        return "QRIS";
    }
}